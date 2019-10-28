
package dev.services;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.controllers.dto.AffichageResultatCommuneDto;
import dev.controllers.dto.CommuneDtoGet;
import dev.controllers.dto.CommuneDtoGetLight;
import dev.controllers.dto.DonneesLocalesHistorique;
import dev.controllers.dto.DonneesLocalesRecherchees;
import dev.controllers.dto.recherche.CommuneCarteDto;
import dev.controllers.dto.recherche.CommuneRechercheDto;
import dev.controllers.dto.recherche.resultat.CommuneDto;
import dev.controllers.dto.recherche.resultat.ConditionMeteoDto;
import dev.controllers.dto.recherche.resultat.PolluantDto;
import dev.controllers.dto.visualiserDonnees.CommuneDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.ConditionMeteoDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.controllers.dto.visualiserDonnees.PolluantDtoVisualisation;
import dev.entities.CodePostal;
import dev.entities.Commune;
import dev.entities.ConditionMeteo;
import dev.entities.DonneesLocales;
import dev.entities.Polluant;
import dev.entities.QualiteAir;
import dev.exceptions.AucuneDonneeException;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.DonneesLocalesException;
import dev.exceptions.IndicateurFuturException;
import dev.repositories.ICommuneRepository;
import dev.repositories.IConditionMeteoRepository;
import dev.repositories.IDonneesLocalesRepository;
import dev.repositories.IPolluantRepository;
import dev.repositories.IQualiteAirRepository;

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
	private IQualiteAirRepository qualiteAirRepository;
	private IConditionMeteoRepository conditionMeteoRepository;
	private IPolluantRepository polluantRepository;
	private AlerteService alerteService;

	@Autowired
	public CommuneService(IDonneesLocalesRepository donneesLocalesRepository, ICommuneRepository communeRepository,
			CodePostalService codePostalService, IQualiteAirRepository qualiteAirRepository,
			IConditionMeteoRepository conditionMeteoRepository, IPolluantRepository polluantRepository,
			AlerteService alerteService) {
		this.donneesLocalesRepository = donneesLocalesRepository;
		this.communeRepository = communeRepository;
		this.codePostalService = codePostalService;
		this.qualiteAirRepository = qualiteAirRepository;
		this.conditionMeteoRepository = conditionMeteoRepository;
		this.polluantRepository = polluantRepository;
		this.alerteService = alerteService;
	}

	/**
	 * Méthode vérifiant si la commune existe déjà dans la base de données.
	 *
	 * @param nomCommune : [String] nom de la commune
	 * @return [Boolean] : true si la commune existe, false sinon
	 */
	public Boolean isCommuneExistante(String nomCommune) {
		return communeRepository.findByNomIgnoreCase(nomCommune).isPresent();
	}

	/**
	 * Méthode récupérant un objet commune de la base de données portant le nom
	 * indiqué.
	 *
	 * @param commune : [String] le nom de la commune à récupérer
	 * @return [Commune] l'objet commune récupéré
	 * @throws CommuneInvalideException : exeception lancée si aucune commune n'a
	 *                                  été trouvée avec ce nom.
	 */
	public Commune recupererCommune(String commune) throws CommuneInvalideException {
		return communeRepository.findByNomIgnoreCase(commune).orElseThrow(
				() -> new CommuneInvalideException("ERREUR " + ": Commune inexistante dans la base de données."));
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
		Optional<ZonedDateTime> date = donneesLocalesRepository.findByCommune(commune);

		if (!date.isPresent()) {
			throw new DonneesLocalesException("Pas de données pour cette commune");
		}

		// Récupération des données locales
		Optional<DonneesLocales> donneesLocales = donneesLocalesRepository.findByCommuneAndDate(commune, date.get());

		if (!donneesLocales.isPresent()) {
			throw new DonneesLocalesException("Pas de données pour cette commune");
		}

		// Récupéartion des id qualiteAir et conditionMeteo
		Integer qualiteAirId = null;
		Integer conditionMeteoId = null;
		if (donneesLocales != null) {
			qualiteAirId = donneesLocales.get().getQualiteAir().getId();
			conditionMeteoId = donneesLocales.get().getConditionMeteo().getId();
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
		donneesLocalesDto.setDate(date.get());
		donneesLocalesDto.setCommune(communeDtoVisualisation);
		if (!qualiteAir.isPresent()) {
			donneesLocalesDto.setListePolluants(null);
		} else {
			List<PolluantDtoVisualisation> listePolluant = polluantRepository.findByQualiteAir(qualiteAir.get());
			donneesLocalesDto.setListePolluants(listePolluant);

		}
		donneesLocalesDto.setConditionMeteo(conditionMeteoDtoVisualisation);

		LOGGER.info("création des données locales à afficher /classe CommuneService");

		return donneesLocalesDto;
	}

	/**
	 * Méthode permettant de récupérer toutes les communes de la base de données.
	 *
	 * @return [List<Commune>] Une liste de toutes les communes de la base de
	 *         données.
	 * @throws CommuneInvalideException : exception lancée si la liste retournée est
	 *                                  null ou vide.
	 */
	public List<Commune> recupererToutesLesCommunesDeLaBase() throws CommuneInvalideException {
		LOGGER.info("recupererToutesLesCommunesDeLaBase() lancée");
		if (communeRepository.findAll() != null && !communeRepository.findAll().isEmpty()) {
			return communeRepository.findAll();
		} else {
			LOGGER.error("CommuneInvalideException : la récupération des communes de la base de données a échoué");
			throw new CommuneInvalideException(
					"ERREUR : la récupération des communes de la base de données a échoué" + ".");
		}
	}

	/**
	 * Méthode permettant de récupérer toutes les communes de la base de données.
	 *
	 * @return [List<Commune>] Une liste de toutes les communes de la base de
	 *         données.
	 * @throws CommuneInvalideException : exception lancée si la liste retournée est
	 *                                  null ou vide.
	 */
	public List<Commune> recupererToutesLesCommunesDeLoireAtlantiqueDeLaBase() {
		LOGGER.info("recupererToutesLesCommunesDeLoireAtlantiqueDeLaBase() " + "lancée");
		communeRepository.findAll();
		if (!communeRepository.findAll().isEmpty()) {
			List<Commune> listeCommunes = communeRepository.findAll();
			List<Commune> listeCommunesDeLA = new ArrayList<>();
			for (Commune commune : listeCommunes) {
				if (commune.getCodeInsee().startsWith("44")) {
					listeCommunesDeLA.add(commune);
				}
			}
			return listeCommunesDeLA;
		} else {
			LOGGER.error("CommuneInvalideException : la récupération des communes de la base de données a échoué");
			throw new CommuneInvalideException(
					"ERREUR : la récupération des communes de la base de données a échoué" + ".");
		}
	}

	/**
	 * Méthode permettant de récupérer toutes les communes de Loire-Altnaituqe de la
	 * base de données.
	 *
	 * @return [List<CommuneDtoGetLight>] Une liste de toutes les communes de
	 *         Loire-Atlantique de la base de données.
	 * @throws CommuneInvalideException : exception lancée si la liste retournée est
	 *                                  null ou vide.
	 */
	public List<CommuneDtoGetLight> recupererToutesLesCommunesDeLaBaseInfosEssentielles()
			throws CommuneInvalideException {
		LOGGER.info("recupererToutesLesCommunesDeLaBaseInfosEssentielles() lancée");
		if (communeRepository.findAllDtoGetLight() != null && !communeRepository.findAllDtoGetLight().isEmpty()) {
			return communeRepository.findAllDtoGetLight();
		} else {
			LOGGER.error("CommuneInvalideException : la récupération des communes de la base de données a échoué");
			throw new CommuneInvalideException(
					"ERREUR : la récupération des communes de la base de données a échoué" + ".");
		}
	}

	/**
	 * Méthode permettant de récupérer toutes les données de communes des Pays de la
	 * Loire de l'API des communes et de les sauvegarder dans la base de données.
	 *
	 * @throws CommuneInvalideException : exception lancée si la récupération a
	 *                                  échoué.
	 */
	public void recupererCommunesDeApi() throws CommuneInvalideException {
		LOGGER.info("recupererCommunesDeApi() lancée");
		LOGGER.info("url de l'api communes = " + URL_API_COMMUNES);

		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<CommuneDtoGet>> response = restTemplate.exchange(URL_API_COMMUNES, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CommuneDtoGet>>() {
					});
			List<CommuneDtoGet> communes = response.getBody();

			for (CommuneDtoGet c : communes) {
				if (isCommuneExistante(c.getNom())) {
					Commune communeExistante = recupererCommune(c.getNom());
					if (communeExistante.getNbHabitants() != c.getPopulation()) {
						communeExistante.setNbHabitants(c.getPopulation());
					}
					communeRepository.save(communeExistante);
				} else {
					LOGGER.warn("La commune " + c.getNom() + " dont le code est " + c.getCode() + " n'a pas été "
							+ "trouvée dans la base de données.");
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
			throw new CommuneInvalideException(
					"ERREUR : la récupération des données de l'API communes a échouché. " + "\n" + e);
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
		ZonedDateTime dateDebutLdt = donneesLocalesRecherchees.getDateDebut();
		// création de la date de fin de recherche
		ZonedDateTime dateFinLdt = donneesLocalesRecherchees.getDateFin();

		// récupération de la commune
		Optional<Commune> commune = communeRepository.findByCodeInsee(codeInsee);
		if (!commune.isPresent()) {
			throw new CommuneInvalideException("Le code insee est incorrect");
		}

		// Récupération de la liste de données locales bornées par les dates
		List<DonneesLocales> listeDonneesLocalesBornees = donneesLocalesRepository.findAllBornesDates(dateDebutLdt,
				dateFinLdt, commune.get());

		// Création de la liste de DonneesLocalesRetourHistorique qui sera retourné par
		// DonneesLocalesHistorique
		List<DonneesLocalesHistorique> listeDonneesLocalesHistorique = new ArrayList<>();

		// Les données locales sont transformée en donnéesLocalesRetourHistorique pour
		// les ajouter à la liste
		for (DonneesLocales donneesLocales : listeDonneesLocalesBornees) {

			// Création de donneesLocalesRetourHistorique qui comprends :
			// -PolluantDtoViualisation
			// -CommuneDtovisualition
			// -Date
			DonneesLocalesHistorique donneesLocalesRetourHistorique = new DonneesLocalesHistorique();

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

			LOGGER.info("donnesLocalesRetourHistorique" + donneesLocalesRetourHistorique.getDate());

			// ajout à la liste qui sera retournée par la classe donneesLocalesHistorique
			listeDonneesLocalesHistorique.add(donneesLocalesRetourHistorique);

			LOGGER.info("liste" + listeDonneesLocalesHistorique);
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
		var resDate = ZonedDateTime.of(commune.getDate(), LocalTime.of(commune.getHeure(), 0), ZoneId.systemDefault());
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

	/**
	 * @return récupère les données qui seront envoyés au front près avoir été
	 *         formatées
	 */
	public List<CommuneCarteDto> recupererDonnesCarte() {
		List<Commune> dixPremieresCommunes = communeRepository.findAll().subList(0, 9);
		return dixPremieresCommunes.stream()
				.map(c -> new CommuneCarteDto(c.getCodeInsee(), c.getCodesPostaux().get(0).getValeur(), c.getNom(),
						c.getLatitude(), c.getLongitude(), alerteService.donnerNiveauAlerte(c)))
				.collect(Collectors.toList());
	}

	/**
	 * @param commune Commune sur laquelle récupérer les informations détaillées
	 * @return Retourne un objet de type Commune Dto pour envoyer les informations
	 *         au controller
	 * @throws AucuneDonneeException
	 */
	public CommuneDto rechercherDetailsCommune(CommuneRechercheDto commune) throws AucuneDonneeException {
		Optional<Commune> c = communeRepository.findByCodeInsee(commune.getCodeInsee());

		if (!c.isPresent()) {
			throw new CommuneInvalideException("La commune recherchée n'existe pas.");
		}

		var resultat = new CommuneDto();
		resultat.setCodeInsee(c.get().getCodeInsee());
		resultat.setNom(c.get().getNom());
		if (commune.getDate() != null) {
			resultat.setDate(commune.getDate());
		}
		if (commune.getHeure() != null) {
			resultat.setHeure(LocalTime.of(commune.getHeure(), 0));
		}
		List<PolluantDto> listePolluants;
		List<DonneesLocales> listeDonnees;
		ConditionMeteoDto donneesMeteo = null;

		// Si des valeurs de recherche temporelles personnalisées ont été renseignées
		if (commune.getDate() != null && commune.getHeure() != null) {

			// récupération des données locales à la période sélectionnée
			listeDonnees = c
					.get().getDonneesLocales().stream().filter(d -> d.getDate().equals(ZonedDateTime
							.of(commune.getDate(), LocalTime.of(commune.getHeure(), 0), ZoneId.systemDefault())))
					.collect(Collectors.toList());

			// vérification de l'approvisionement des données
			if (listeDonnees.isEmpty()) {
				throw new AucuneDonneeException("Aucune données disponible pour ces paramètres");
			}

			// Valorisation des conditions météorologiques et de la liste des polluants
			listePolluants = listeDonnees.get(0).getQualiteAir().getListePolluants().stream()
					.map(p -> new PolluantDto(p.getNom(), p.getUnite(), p.getValeur())).collect(Collectors.toList());

			ConditionMeteo cm = listeDonnees.get(0).getConditionMeteo();
			donneesMeteo = new ConditionMeteoDto(cm.getHumidite(), cm.getEnsoleillement(), cm.getTemperature());
		}

		else {

			listeDonnees = c.get().getDonneesLocales();
			if (listeDonnees.isEmpty()) {
				throw new AucuneDonneeException("Aucune données disponible pour ces paramètres");
			}
			listePolluants = listeDonnees.get(listeDonnees.size() - 1).getQualiteAir().getListePolluants().stream()
					.map(p -> new PolluantDto(p.getNom(), p.getUnite(), p.getValeur())).collect(Collectors.toList());

			ConditionMeteo cm = listeDonnees.get(listeDonnees.size() - 1).getConditionMeteo();
			donneesMeteo = new ConditionMeteoDto(cm.getHumidite(), cm.getEnsoleillement(), cm.getTemperature());
			resultat.setDate(listeDonnees.get(listeDonnees.size() - 1).getDate().toLocalDate());
			resultat.setHeure(listeDonnees.get(listeDonnees.size() - 1).getDate().toLocalTime());
		}

		// si des valeurs de recherche par polluant on été renseignées
		if (commune.getPolluant() != null) {
			listePolluants = listePolluants.stream().filter(p -> p.getNom().equals(commune.getPolluant()))
					.collect(Collectors.toList());
			if (listePolluants.isEmpty()) {
				throw new AucuneDonneeException("Aucune données disponible pour ces paramètres");
			}
		}

		resultat.setPolluants(listePolluants);
		resultat.setNbHabitants(c.get().getNbHabitants());
		resultat.setMeteo(donneesMeteo);
		resultat.setNiveauAlerte(commune.getAlerte());

		return resultat;

	}

	/**
	 * @return Renvoie la liste entière des communes de loire atlantique
	 */
	public List<CommuneCarteDto> recupererToutesCommunes() {

		return communeRepository.findAll().stream()
				.map(c -> new CommuneCarteDto(c.getCodeInsee(), c.getCodesPostaux().get(0).getValeur(), c.getNom(),
						c.getLatitude(), c.getLongitude(), alerteService.donnerNiveauAlerte(c)))
				.collect(Collectors.toList());
	}

}
