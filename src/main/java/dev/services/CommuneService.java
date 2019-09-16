package dev.services;

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
import dev.controllers.dto.CommuneDto;
import dev.controllers.dto.CommuneDtoGet;
import dev.controllers.dto.CommuneRechercheDto;
import dev.controllers.dto.visualiserDonnees.CommuneDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.ConditionMeteoDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.controllers.dto.visualiserDonnees.PolluantDtoVisualisation;
import dev.entities.CodePostal;
import dev.entities.Commune;
import dev.entities.ConditionMeteo;
import dev.entities.DonneesLocales;
import dev.entities.QualiteAir;
import dev.exceptions.CommuneInvalideException;
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

	private IDonneesLocalesRepository donneesLocalesRepository;

	@Value("${url.communes_api}")
	private String URL_API_COMMUNES;

	private ICommuneRepository communeRepository;
	private CodePostalService codePostalService;

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

	public Boolean isCommuneExistante(String nomCommune) {
		return communeRepository.findByNomIgnoreCase(nomCommune).isPresent();
	}

	public Commune recupererCommune(String commune) {
		return communeRepository.findByNomIgnoreCase(commune).orElseThrow(
				() -> new CommuneInvalideException("ERREUR " + ": Commune inexistante dans la base de données."));
	}

	/**
	 * Méthode qui permet de créer les données locales à partir des différentes
	 * entités
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

	public List<CommuneDto> recupererToutesLesCommunesDto() {
		return communeRepository.findAllWithCodeDenomination();
	}

	public void recupererCommunesDeApi() throws CommuneInvalideException {

		LOGGER.info("recupererCommunesDeApi() lancé");

		LOGGER.info("url de l'api = " + URL_API_COMMUNES);

		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<CommuneDtoGet>> response = restTemplate.exchange(URL_API_COMMUNES, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<CommuneDtoGet>>() {
					});
			List<CommuneDtoGet> communes = response.getBody();

			for (CommuneDtoGet c : communes) {
				Commune commune = new Commune(c.getNom(), c.getPopulation(), c.getCode().toString(),
						c.getCentre().getCoordinates().get(1), c.getCentre().getCoordinates().get(0));
				communeRepository.save(commune);
				for (String cp : c.getCodesPostaux()) {
					CodePostal codePostal = new CodePostal(cp, commune);
					codePostalService.sauvegarderCodePostal(codePostal);
				}
			}

			LOGGER.info(communes.toString());
		} catch (Exception e) {
			throw new CommuneInvalideException(
					"ERREUR : la récupération des données de l'API communes a échouché. " + "\n" + e);
		}
	}

	/**
	 * @param commune Les paramètres de recherche renseignés par l'utilisateur pour
	 *                afficher les données d'un commune particulière à un instant T,
	 *                éventuellement sur un polluant spécifique
	 * @return renvoie le résultat de la recherche sous la forme d'une classe DTO
	 *         adaptée
	 * @throws IndicateurFuturException Déclenchée si l'utilisateur a indiqué une
	 *                                  date future
	 */
	public AffichageResultatCommuneDto rechercheCommune(CommuneRechercheDto commune) throws IndicateurFuturException {
		// récupération de la commune
		var oResCommune = communeRepository.findByNomIgnoreCase(commune.getNomCommune());
		if (!oResCommune.isPresent()) {
			throw new CommuneInvalideException("Cette commune n'existe pas.");
		}
		var resCommune = oResCommune.get();
		// vérification de la validité de la date saisie
		var resDate = ZonedDateTime.of(commune.getDate(), commune.getHeure(), ZoneId.systemDefault());
		List<DonneesLocales> resDonnees = new ArrayList<>();
		if (resDate.isAfter(ZonedDateTime.now())) {
			throw new IndicateurFuturException("Cette date est dans le futur");
		}
		// filtrage des données à la date saisie

		else {
			resDonnees = resCommune.getDonneesLocales().stream().filter(d -> d.getDate().equals(resDate))
					.collect(Collectors.toList());
		}

		// filtrage (optionnel) des polluants
		if (commune.getPolluant() != null) {
			resDonnees.get(0).getQualiteAir().setListePolluants(resDonnees.get(0).getQualiteAir().getListePolluants()
					.stream().filter(p -> p.getNom().equals(commune.getPolluant())).collect(Collectors.toList()));
		}

		// envoi de la réponse sous la forme de l'objet à afficher
		return new AffichageResultatCommuneDto(resCommune.getNom(), resDate, null, resDonnees.get(0),
				resCommune.getNbHabitants());

	}
}
