package dev.services;

import dev.controllers.dto.*;
import dev.controllers.dto.visualiserDonnees.CommuneDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.ConditionMeteoDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.controllers.dto.visualiserDonnees.PolluantDtoVisualisation;
import dev.entities.*;
import dev.exceptions.AucuneDonneeException;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.IndicateurFuturException;
import dev.repositories.*;
import dev.utils.CalculUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Classe regroupant les services d'une commune géographique.
 */
@Service
public class CommuneService {


	private final Logger LOGGER = LoggerFactory.getLogger(CommuneService.class);

    @Value("${url.communes_api}")
    private String URL_API_COMMUNES;


    private IDonneesLocalesRepository donneesLocalesRepository;
    private ICommuneRepository communeRepository;
    private CodePostalService codePostalService;
    private CalculUtils calculUtils;
    private IQualiteAirRepository qualiteAirRepository;
    private IConditionMeteoRepository conditionMeteoRepository;
    private IPolluantRepository polluantRepository;


    @Autowired
    public CommuneService(IDonneesLocalesRepository donneesLocalesRepository, ICommuneRepository communeRepository,
                          CodePostalService codePostalService, IQualiteAirRepository qualiteAirRepository,
                          IConditionMeteoRepository conditionMeteoRepository, IPolluantRepository polluantRepository) {
        this.donneesLocalesRepository = donneesLocalesRepository;
        this.communeRepository = communeRepository;
        this.codePostalService = codePostalService;
        this.qualiteAirRepository = qualiteAirRepository;
        this.conditionMeteoRepository = conditionMeteoRepository;
        this.polluantRepository = polluantRepository;
    }

    /**
     * Méthode vérifiant si la commune existe déjà dans la base de données.
     * @param nomCommune : [String] nom de la commune
     * @return [Boolean] : true si la commune existe, false sinon
     */

    public Boolean isCommuneExistante(String nomCommune) {
        return communeRepository.findByNomIgnoreCase(nomCommune).isPresent();
    }


    /**
     * Méthode récupérant un objet commune de la base de données portant le nom indiqué.
     * @param commune : [String] le nom de la commune à récupérer
     * @return [Commune] l'objet commune récupéré
     * @throws CommuneInvalideException : exeception lancée si aucune commune n'a été trouvée avec ce nom.
     */
    public Commune recupererCommune(String commune) throws CommuneInvalideException {
        return communeRepository.findByNomIgnoreCase(commune).orElseThrow(() -> new CommuneInvalideException("ERREUR " +
                ": Commune inexistante dans la base de données."));
    }

	/**
	 * Méthode qui permet de créer un objet donnéeslocalesDto pour une commune
	 *
	 * @param codeInsee
	 * @return
	 */
	public DonneesLocalesDto creerDonneesLocalesCommune(String codeInsee) {

		// récupération de la commune
		Optional<Commune> commune = communeRepository.findByCodeInsee(codeInsee);

		if (!commune.isPresent()) {
			throw new CommuneInvalideException("La commune n'existe pas");
		}

		// Récupération de l'objet communeDtovisualisation
		CommuneDtoVisualisation communeDtoVisualisation = new CommuneDtoVisualisation(commune.get().getNom(),
				commune.get().getNbHabitants());

		// Récupération de la dernière date d'enregistrements pour la commune
		ZonedDateTime date = donneesLocalesRepository.findByCommune(commune);
		// Récupération des données locales
		DonneesLocales donneesLocales = donneesLocalesRepository.findByCommuneAndDate(commune, date);

		// Récupéartion des id qualiteAir et conditionMeteo
		Integer qualiteAirId = null;
		Integer conditionMeteoId = null;
		if (donneesLocales != null) {
			qualiteAirId = donneesLocales.getQualiteAir().getId();
			conditionMeteoId = donneesLocales.getConditionMeteo().getId();
		}

		// Récupération qualitéAir
		Optional<QualiteAir> qualiteAir = null;
		if (qualiteAirId != null) {
			qualiteAir = qualiteAirRepository.findById(qualiteAirId);
		}

		// Récupération ConditionMeteo et création du conditionMeteoDtoVisualisation
		ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation = null;
		if (conditionMeteoId != null) {
			Optional<ConditionMeteo> conditionMeteo = conditionMeteoRepository.findById(conditionMeteoId);
			conditionMeteoDtoVisualisation = new ConditionMeteoDtoVisualisation(
					conditionMeteo.get().getEnsoleillement(), conditionMeteo.get().getTemperature(),
					conditionMeteo.get().getHumidite());
		}
		// Création de l'objet DonneesLocalesDto
		DonneesLocalesDto donneesLocalesDto = new DonneesLocalesDto();
		donneesLocalesDto.setDate(date);
		donneesLocalesDto.setCommuneDtoVisualisation(communeDtoVisualisation);
		if (!qualiteAir.isPresent()) {
			donneesLocalesDto.setListePolluantDtoVisualisation(null);
		} else {
			List<PolluantDtoVisualisation> listePolluant = polluantRepository.findByQualiteAir(qualiteAir.get());
			donneesLocalesDto.setListePolluantDtoVisualisation(listePolluant);

		}
		donneesLocalesDto.setConditionMeteoDtoVisualisation(conditionMeteoDtoVisualisation);

		LOGGER.info("création des données locales à afficher /classe CommuneService");

		return donneesLocalesDto;
	}


    /**
     * Méthode permettant de récupérer toutes les communes de la base de données.
     * @return [List<Commune>] Une liste de toutes les communes de la base de données.
     * @throws CommuneInvalideException : exception lancée si la liste retournée est null ou vide.
     */
    public List<Commune> recupererToutesLesCommunesDeLaBase() throws CommuneInvalideException {
        LOGGER.info("recupererToutesLesCommunesDeLaBase() lancée");
        if(communeRepository.findAll() != null && !communeRepository.findAll().isEmpty()) {
            return communeRepository.findAll();
        } else {
            LOGGER.error("CommuneInvalideException : la récupération des communes de la base de données a échoué");
            throw new CommuneInvalideException("ERREUR : la récupération des communes de la base de données a échoué" +
                    ".");
        }
    }

    /**
     * Méthode permettant de récupérer toutes les données de communes des Pays de la Loire de l'API des communes et
     * de les sauvegarder dans la base de données.
     * @throws CommuneInvalideException : exception lancée si la récupération a échoué.
     */
    public void recupererCommunesDeApi() throws CommuneInvalideException {
        LOGGER.info("recupererCommunesDeApi() lancée");
        LOGGER.info("url de l'api communes = " + URL_API_COMMUNES);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<CommuneDtoGet>> response = restTemplate.exchange(
                    URL_API_COMMUNES,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CommuneDtoGet>>() {
                    });
            List<CommuneDtoGet> communes = response.getBody();

            for (CommuneDtoGet c : communes) {
                if(isCommuneExistante(c.getNom())) {
                    Commune communeExistante = recupererCommune(c.getNom());
                    if(communeExistante.getNbHabitants() != c.getPopulation()) {
                        communeExistante.setNbHabitants(c.getPopulation());
                    }
                    communeRepository.save(communeExistante);
                } else {
                    LOGGER.warn("La commune " + c.getNom() + " dont le code est " + c.getCode() + " n'a pas été " +
                            "trouvée dans la base de données.");
                    Commune commune = new Commune(c.getNom(), c.getPopulation(), c.getCode().toString(),
                            c.getCentre().getCoordinates().get(1), c.getCentre().getCoordinates().get(0));
                    communeRepository.save(commune);
                    for (String cp : c.getCodesPostaux()) {
                        CodePostal codePostal = new CodePostal(cp, commune);
                        codePostalService.sauvegarderCodePostal(codePostal);
                    }
                }
            }
        } catch (RuntimeException e) {
            LOGGER.error("RuntimeException, CommuneInvalideException \n" + e);
            throw new CommuneInvalideException("ERREUR : la récupération des données de l'API communes a échouché. " +
                    "\n" + e);
        }

}
	/**
	 * Méthode qui permet de créer un historique de données en fonction des infos
	 * saisi par l'utilisateur
	 *
	 * @param donneesLocalesRecherchees
	 * @param codeInsee
	 * @return
	 */
	public List<DonneesLocalesHistorique> creerHistorique(DonneesLocalesRecherchees donneesLocalesRecherchees,
			String codeInsee) {

		// création de la date de début de recherche
		LocalDateTime dateDebutLdt = donneesLocalesRecherchees.getDateDebut()
				.atTime(donneesLocalesRecherchees.getHeureDebut());
		ZonedDateTime dateDebut = dateDebutLdt.atZone(ZoneId.systemDefault());
		// création de la date de fin de recherche
		LocalDateTime dateFinLdt = donneesLocalesRecherchees.getDateFin()
				.atTime(donneesLocalesRecherchees.getHeureFin());
		ZonedDateTime dateFin = dateFinLdt.atZone(ZoneId.systemDefault());

		// récupération de la commune
		Optional<Commune> commune = communeRepository.findByCodeInsee(codeInsee);
		if (!commune.isPresent()) {
			throw new CommuneInvalideException("Le code insee est incorrect");
		}

		// Récupération de la liste de données locales bornées par les dates
		List<DonneesLocales> listeDonneesLocalesBornees = donneesLocalesRepository
				.findAllByDateDebutAndDateFin(dateDebut, dateFin, commune.get());

		// Création de la liste de DonneesLocalesRetourHistorique qui sera retourné par
		// DonneesLocalesHistorique
		List<DonneesLocalesHistorique> listeDonneesLocalesHistorique = new ArrayList<>();

		// Création de donneesLocalesRetourHistorique qui comprends :
		// -PolluantDtoViualisation
		// -CommuneDtovisualition
		// -Date
		DonneesLocalesHistorique donneesLocalesRetourHistorique = new DonneesLocalesHistorique();

		// Les données locales sont transformée en donnéesLocalesRetourHistorique pour
		// les ajouter à la liste
		for (DonneesLocales donneesLocales : listeDonneesLocalesBornees) {
			// création du polluantDtoVisualisation
			PolluantDtoVisualisation polluantDtoVisualisation = new PolluantDtoVisualisation();
			// récupération de la liste de polluant de la donnée locale
			List<Polluant> listePolluant = donneesLocales.getQualiteAir().getListePolluants();
			// recherche sur le nom du polluant pour retourner l'objet
			// polluantDtoVisualisation
			for (Polluant polluants : listePolluant) {
				if (polluants.getNom().equals(donneesLocalesRecherchees.getPolluant())) {
					polluantDtoVisualisation.setNom(polluants.getNom());
					polluantDtoVisualisation.setUnite(polluants.getUnite());
					polluantDtoVisualisation.setValeur(polluants.getValeur());
				}
			}
			// Création de la communeDtoVisualisation
			CommuneDtoVisualisation communeDtoVisualisation = new CommuneDtoVisualisation(
					donneesLocales.getCommune().getNom(), donneesLocales.getCommune().getNbHabitants());
			// Création de la date
			ZonedDateTime date = donneesLocales.getDate();

			// Remplissage de l'objet donneesLocalesRetourHistorique
			donneesLocalesRetourHistorique.setCommuneDtoVisualisation(communeDtoVisualisation);
			donneesLocalesRetourHistorique.setDate(date);
			donneesLocalesRetourHistorique.setPolluantDtoVisualisation(polluantDtoVisualisation);

			// ajout à la liste qui sera retournée par la classe donneesLocalesHistorique
			listeDonneesLocalesHistorique.add(donneesLocalesRetourHistorique);
		}

		return listeDonneesLocalesHistorique;
	}


	/**
	 * @param commune Les paramètres de recherche renseignés par l'utilisateur pour
	 *                afficher les données d'un commune particulière à un instant T,
	 *                éventuellement sur un polluant spécifique
	 * @return renvoie le résultat de la recherche sous la forme d'une classe DTO
	 *         adaptée
	 * @throws IndicateurFuturException Déclenchée si l'utilisateur a indiqué une
	 *                                  date future
	 * @throws AucuneDonneeException    Déclenchée si aucune donnée n'est disponible
	 */
	public AffichageResultatCommuneDto rechercheCommune(CommuneRechercheDto commune)
			throws IndicateurFuturException, AucuneDonneeException {
		// récupération de la commune
		var oResCommune = communeRepository.findByNomIgnoreCase(commune.getNomCommune());
		if (!oResCommune.isPresent()) {
			throw new CommuneInvalideException("Cette commune n'existe pas.");
		}
		var resCommune = oResCommune.get();

		// Récupération de la date
		var resDate = ZonedDateTime.of(commune.getDate(), commune.getHeure(), ZoneId.systemDefault());
		List<DonneesLocalesDto> resDonnees = new ArrayList<>();

		// récupération des polluants
		List<Polluant> polluants = resCommune.getDonneesLocales().get(0).getQualiteAir().getListePolluants();
		// filtrage optionnel des polluants
		if (commune.getPolluant() != null) {
			polluants = polluants.stream().filter(p -> p.getNom().equals(commune.getPolluant()))
					.collect(Collectors.toList());
		}

		// conversion des informations polluants en données affichables
		List<PolluantDtoVisualisation> listePolluants = polluants.stream()
				.map(p -> new PolluantDtoVisualisation(p.getNom(), p.getValeur(), p.getUnite()))
				.collect(Collectors.toList());

		// vérification de la validité de la date saisie
		if (resDate.isAfter(ZonedDateTime.now())) {
			throw new IndicateurFuturException("Cette date est dans le futur");
		}
		// filtrage des données à la date saisie

		else {
			resDonnees = resCommune.getDonneesLocales().stream().filter(d -> d.getDate().equals(resDate))
					.map(d -> new DonneesLocalesDto(
							new CommuneDtoVisualisation(resCommune.getNom(), resCommune.getNbHabitants()),
							listePolluants,
							new ConditionMeteoDtoVisualisation(d.getConditionMeteo().getEnsoleillement(),
									d.getConditionMeteo().getTemperature(), d.getConditionMeteo().getHumidite()),
							resDate))
					.collect(Collectors.toList());
		}

		// vérification de la présence de données
		if (resDonnees.isEmpty()) {
			throw new AucuneDonneeException("Aucune donnée disponible avec ces paramètres.");
		}

		// envoi de la réponse sous la forme de l'objet à afficher
		return new AffichageResultatCommuneDto(resCommune.getNom(), resDate, null, resDonnees.get(0),
				resCommune.getNbHabitants());

	}

}
