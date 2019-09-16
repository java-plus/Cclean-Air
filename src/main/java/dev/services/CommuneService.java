package dev.services;


import dev.controllers.dto.CommuneDtoGet;
import dev.controllers.dto.visualiserDonnees.CommuneDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.ConditionMeteoDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.controllers.dto.visualiserDonnees.PolluantDtoVisualisation;
import dev.entities.*;
import dev.exceptions.CommuneInvalideException;
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

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

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
    private CalculUtils calculUtils;

    private IQualiteAirRepository qualiteAirRepository;

    private IConditionMeteoRepository conditionMeteoRepository;

    private IPolluantRepository polluantRepository;

    @Autowired
    public CommuneService(IDonneesLocalesRepository donneesLocalesRepository, ICommuneRepository communeRepository,
                          CodePostalService codePostalService, CalculUtils calculUtils,
                          IQualiteAirRepository qualiteAirRepository,
                          IConditionMeteoRepository conditionMeteoRepository, IPolluantRepository polluantRepository) {
        this.donneesLocalesRepository = donneesLocalesRepository;
        this.communeRepository = communeRepository;
        this.codePostalService = codePostalService;
        this.calculUtils = calculUtils;
        this.qualiteAirRepository = qualiteAirRepository;
        this.conditionMeteoRepository = conditionMeteoRepository;
        this.polluantRepository = polluantRepository;
    }

    public Boolean isCommuneExistante(String nomCommune) {
        return communeRepository.findByNomIgnoreCase(nomCommune).isPresent();
    }

    public Commune recupererCommune(String commune) throws CommuneInvalideException {
        return communeRepository.findByNomIgnoreCase(commune).orElseThrow(() -> new CommuneInvalideException("ERREUR " +
                ": Commune inexistante dans la base de données."));
    }

    /**
     * Méthode qui permet de créer les données locales à partir des différentes entités
     *
     * @param codeInsee
     * @return
     */
    public DonneesLocalesDto creerDonneesLocalesCommune(String codeInsee) {

        //récupération de la commune
        Optional<Commune> commune = communeRepository.findByCodeInsee(codeInsee);

        if (!commune.isPresent()) {
            throw new CommuneInvalideException("La commune n'existe pas");
        }

        //Récupération de l'objet communeDtovisualisation
        CommuneDtoVisualisation communeDtoVisualisation = new CommuneDtoVisualisation(commune.get().getNom(), commune.get().getNbHabitants());

        //Récupération de la dernière date d'enregistrements pour la commune
        ZonedDateTime date = donneesLocalesRepository.findByCommune(commune);
        //Récupération des données locales
        DonneesLocales donneesLocales = donneesLocalesRepository.findByCommuneAndDate(commune, date);

        //Récupéartion des id qualiteAir et conditionMeteo
        Integer qualiteAirId = null;
        Integer conditionMeteoId = null;
        if (donneesLocales != null) {
            qualiteAirId = donneesLocales.getQualiteAir().getId();
            conditionMeteoId = donneesLocales.getConditionMeteo().getId();
        }

        //Récupération qualitéAir
        Optional<QualiteAir> qualiteAir = null;
        if (qualiteAirId != null) {
            qualiteAir = qualiteAirRepository.findById(qualiteAirId);
        }

        //Récupération ConditionMeteo et création du conditionMeteoDtoVisualisation
        ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation = null;
        if (conditionMeteoId != null) {
            Optional<ConditionMeteo> conditionMeteo = conditionMeteoRepository.findById(conditionMeteoId);
            conditionMeteoDtoVisualisation = new ConditionMeteoDtoVisualisation(conditionMeteo.get().getEnsoleillement(), conditionMeteo.get().getTemperature(), conditionMeteo.get().getHumidite());
        }
        //Création de l'objet DonneesLocalesDto
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
            throw new CommuneInvalideException("ERREUR : la récupération des communes de la base de données a échouée" +
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
                Commune commune = new Commune(c.getNom(), c.getPopulation(), c.getCode().toString(),
                        c.getCentre().getCoordinates().get(1), c.getCentre().getCoordinates().get(0));
                communeRepository.save(commune);
                for (String cp : c.getCodesPostaux()) {
                    CodePostal codePostal = new CodePostal(cp, commune);
                    codePostalService.sauvegarderCodePostal(codePostal);
                }
            }
        } catch (RuntimeException e) {
            LOGGER.info("RuntimeException, CommuneInvalideException \n" + e);
            throw new CommuneInvalideException("ERREUR : la récupération des données de l'API communes a échouché. " +
                    "\n" + e);
        }
    }
}
