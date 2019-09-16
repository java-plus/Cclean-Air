package dev.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.controllers.dto.qualiteAir.Mesure;
import dev.controllers.dto.qualiteAir.QualiteAirDtoApi;
import dev.entities.Commune;
import dev.entities.Polluant;
import dev.entities.QualiteAir;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.QualiteAirInvalideException;
import dev.repositories.IQualiteAirRepository;
import dev.utils.CalculUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe regroupant les méthodes de service associées à la qualité de l'air.
 */
@Service
public class QualiteAirService {

    private final Logger LOGGER = LoggerFactory.getLogger(QualiteAirService.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private PolluantService polluantService;
    private IQualiteAirRepository qualiteAirRepository;
    private CommuneService communeService;
    private CalculUtils calculUtils;
    @Value("${url.pollution_api}")
    private String URL_API_POLLUTION;

    @Autowired
    public QualiteAirService(PolluantService polluantService, IQualiteAirRepository qualiteAirRepository,
                             CommuneService communeService, CalculUtils calculUtils) {
        this.polluantService = polluantService;
        this.qualiteAirRepository = qualiteAirRepository;
        this.communeService = communeService;
        this.calculUtils = calculUtils;
    }

    /**
     * Méthode récupérant les données de la qualité de l'air de l'api pour chaque polluant et pour chaque station de
     * mesure.
     * @return Map<String, List<Polluant>> : une map contenant pour chaque station (key=le nom de la station), la liste
     * des polluants (value).
     */
    public Map<String, List<Polluant>> recupererDonneesQualiteAirDeApi() {
        LOGGER.info("recupererEtSauvegarderQualiteAir() lancé");

        Map<String, List<Polluant>> mapPolluants = new HashMap<>();

        StringBuilder sbUrlDonneesPollution = new StringBuilder(URL_API_POLLUTION);
        sbUrlDonneesPollution.append("/mesures?debut=");
        sbUrlDonneesPollution.append(formatter.format(ZonedDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0)));
        sbUrlDonneesPollution.append("&fin=");
        sbUrlDonneesPollution.append(formatter.format(ZonedDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0)));
        sbUrlDonneesPollution.append("&zones=all&polluant=");

        List<Polluant> polluants = polluantService.recupererPolluantsDeApi();

        for (Polluant p : polluants) {
            String urlDonneesPollutionDuPolluant = sbUrlDonneesPollution.toString() + p.getCode();
            LOGGER.info("url utilisée = " + urlDonneesPollutionDuPolluant);

            try {
                RestTemplate restTemplate = new RestTemplate();

                String result = restTemplate.getForObject(urlDonneesPollutionDuPolluant.toString(), String.class);
                result = result.replaceAll("\"[0-9]*\":", "").replaceAll("\"mesures\":\\{", "\"mesures\":[")
                        .replaceAll("\\}\\}\\}", "}]}");

                QualiteAirDtoApi qualiteAirDtoApi = null;
                ObjectMapper mapper = new ObjectMapper();
                qualiteAirDtoApi = mapper.readValue(result, QualiteAirDtoApi.class);

                for (Mesure m : qualiteAirDtoApi.getMesures()) {
                    Polluant polluant = new Polluant(m.getData().get(0).getUnite(),
                            p.getNom(), p.getCode(), m.getData().get(0).getNiveau());
                    String nomVilleStation = "";

                    if (communeService.isCommuneExistante(m.getName())) {
                        nomVilleStation = m.getName();
                    } else {
                        nomVilleStation = m.getZone();
                    }

                    if (mapPolluants.containsKey(nomVilleStation)) {
                        List<Polluant> listePolluants = (List<Polluant>) mapPolluants.get(nomVilleStation);
                        listePolluants.add(polluant);
                    } else {
                        List<Polluant> listePolluants = new ArrayList<>();
                        listePolluants.add(polluant);
                        mapPolluants.put(nomVilleStation, listePolluants);
                    }
                }

            } catch (RestClientException e) {
                throw new QualiteAirInvalideException("ERREUR : la récupération des données de l'API " +
                        "de qualité d'air a échoué. " +
                        "\n" + e);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return mapPolluants;
    }

    /**
     * Méthode sauvegardant la liste des polluants pour chaque station dans la table QualiteAir.
     * @param mapPolluants Map<String, List<Polluant>> : une map contenant pour chaque station (key=le nom de
     *                     la station), la liste des polluants (value).
     */
    public void sauvegarderQualiteAir(Map<String, List<Polluant>> mapPolluants) {
        mapPolluants.forEach(
                (key, value) -> {
                    QualiteAir qualiteAir =
                            new QualiteAir(ZonedDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0),
                                    key);
                    qualiteAirRepository.save(qualiteAir);
                    value.forEach(polluant -> polluant.setQualiteAir(qualiteAir));
                    polluantService.sauvegarderPolluant(value);
                }
        );
    }

    /**
     * Méthode retournant la date du dernier enregistrement dans la table de la qualité d'air.
     * @return : [ZonedDateTime] la date/heure du dernier enregistrement.
     * @throws QualiteAirInvalideException : exception dans le cas où aucune ligne n'a été trouvée.
     */
    public ZonedDateTime recupererDateDerniereQualiteAirDeLaBase() throws QualiteAirInvalideException {
        QualiteAir qualiteAir =
                qualiteAirRepository.findFirstByOrderByIdDesc().orElseThrow(() -> new QualiteAirInvalideException("ERREUR" +
                " : " +
                "Impossible " +
                "de trouver les dernières données de qualité d'air."));
        return qualiteAir.getDate();
    }

    /**
     * Méthode retournant la liste des QualiteAir de la date/heure indiquée de la base de données.
     * @param date : [ZonedDateTime] date/heure des QualiteAir à retourner.
     * @return [List<QualiteAir>] la liste des QualiteAir.
     */
    public List<QualiteAir> recupererListeQualitesAirSelonDate(ZonedDateTime date) {
        return qualiteAirRepository.findByDate(date);
    }

    /**
     * Méthode retournant la QualiteAir dont la station était la plus proche des coordonnées indiquées.
     * @param listeQualiteAir : [List<QualiteAir>] liste des QualiteAir où chercher.
     * @param longitude : [Double] longitude de la commune.
     * @param latitude : [Double] latitude de la commune.
     * @return [QualiteAir] la QualiteAir la plus proche géographiquement de la commune.
     */
    public QualiteAir recupererQualiteAirCorrespondantACoordonnees(List<QualiteAir> listeQualiteAir, Double longitude,
                                                                   Double latitude) {
        Double distance = Double.MAX_VALUE;
        QualiteAir qualiteAirCorrespondante = null;

        for (QualiteAir q : listeQualiteAir) {
            Commune communeDeLaStation = null;
            try {
                communeDeLaStation = communeService.recupererCommune(q.getStationVille());
            } catch(CommuneInvalideException e) {
                LOGGER.info("CommuneInvalideException" + e);
                continue;
            }

            Double resultat = calculUtils.calculerDistanceEntreDeuxPoints(communeDeLaStation.getLongitude(),
                    communeDeLaStation.getLatitude(), longitude, latitude);
            if(resultat < distance) {
                qualiteAirCorrespondante = q;
                distance = resultat;
            }
        }
        return qualiteAirCorrespondante;
    }

    /**
     * Méthode permettant de supprimer de la base de données les données de qualité d'air antérieures à la date-heure
     * indiquée.
     * @param dateExpiration : [ZonedDateTime] date-heure au-delà de laquelle les données sont à supprimer
     */
    public void purgerQualiteAir(ZonedDateTime dateExpiration) {
        LOGGER.info("purgerQualiteAir()");
        qualiteAirRepository.deleteAllExpiredSince(dateExpiration);
    }

    public List<QualiteAir> recupererLesQualitesAirAvantDate(ZonedDateTime dateHeureLimite) throws QualiteAirInvalideException {
        LOGGER.info("recupererQualiteAirAvantDate() lancé, dateheure = " + dateHeureLimite);
        try {
            return qualiteAirRepository.findByDateBefore(dateHeureLimite);
        } catch(RuntimeException e) {
            LOGGER.error("QualiteAirInvalideException \n" + e);
            throw new QualiteAirInvalideException("ERREUR : impossible de récupérer la liste des qualités d'air. \n" + e);
        }
    }
}
