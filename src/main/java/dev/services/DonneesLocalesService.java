package dev.services;

import dev.controllers.dto.CommuneDto;
import dev.entities.*;
import dev.exceptions.DonneesLocalesException;
import dev.repositories.IDonneesLocalesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

/**
 * Classe regroupant les services liés aux données locales.
 */
@Service
public class DonneesLocalesService {

    private Logger LOGGER = LoggerFactory.getLogger(DonneesLocalesService.class);

    @Autowired
    private IDonneesLocalesRepository donneesLocalesRepository;
    @Autowired
    private ConditionMeteoService conditionMeteoService;
    @Autowired
    private QualiteAirService qualiteAirService;
    @Autowired
    private CommuneService communeService;

    /**
     * Méthode assurant la sauvegarde des données locales dans la base de données.
     * @param zonedDateTimeDesDonneesLocales [ZonedDateTime] Date/heure des données locales à générer.
     * @param nbCommunes [Integer] Quantité des communes pour lequels générer des données locales.
     */
    public void genererEtsauvegarderDonneesLocales(ZonedDateTime zonedDateTimeDesDonneesLocales, Integer nbCommunes) {

        List<Commune> listeCommunes = communeService.recupererToutesLesCommunesDeLaBase();
        Map<String, List<Polluant>> donneesPollution = qualiteAirService.recupererDonneesQualiteAirDeApi();
        qualiteAirService.sauvegarderQualiteAir(donneesPollution);
        List<QualiteAir> listeQualiteAir = qualiteAirService.recupererListeQualitesAirSelonDate(qualiteAirService.recupererDateDerniereQualiteAirDeLaBase());

        try {
            for (int i = 0; i < nbCommunes; i++) {
                Commune c = listeCommunes.get(i);
                ConditionMeteo conditionMeteo =
                        conditionMeteoService.recupererConditionMeteoCommuneDeApi(new CommuneDto(c.getNom(),
                                c.getNbHabitants(), c.getCodeInsee(), c.getLatitude(), c.getLongitude()));
                QualiteAir qualiteAir = qualiteAirService.recupererQualiteAirCorrespondantACoordonnees(listeQualiteAir,
                        c.getLongitude(),
                        c.getLongitude());
                DonneesLocales donneesLocales =
                        new DonneesLocales(zonedDateTimeDesDonneesLocales,
                                c, conditionMeteo, qualiteAir);
                conditionMeteoService.sauvegarderConditionMeteo(conditionMeteo);
                donneesLocalesRepository.save(donneesLocales);
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new DonneesLocalesException("ERREUR : impossible de générer des données locales pour autant de " +
                    "communes. \n" +e);
        }


    }

    /**
     * Méthode permettant de supprimer de la base de données les données locales antérieures à la date-heure indiquée.
     * @param zonedDateTime : [ZonedDateTime] date-heure au-delà de laquelle les données sont à supprimer
     */
    public void purgerDonneesLocales(ZonedDateTime zonedDateTime) {
        LOGGER.info("purgerDonneesLocales() lancé");
        LOGGER.info("date-heure d'expiration = " + zonedDateTime);
        donneesLocalesRepository.deleteAllExpiredSince(zonedDateTime);
    }

    /**
     * Méthode récupérant la date-heure de la dernière ligne de données locales enregistrée dans la base de données.
     * @return : [ZonedDateTime] date-heure de la dernière ligne de données locales enregistrée dans la base de données.
     * @throws DonneesLocalesException : exception envoyée dans le cas où aucune ligne n'a été trouvée.
     */
    public ZonedDateTime recupererDateHeureDeDerniereDonneesLocales() throws DonneesLocalesException {
        LOGGER.info("recupererDateHeureDeDerniereDonneesLocales() lancé");
        DonneesLocales donneesLocales =
                donneesLocalesRepository.findTopByOrderByDateDesc().orElseThrow(() -> new DonneesLocalesException(
                        "ERREUR : Impossible de récupérer les dernières données locales enregistrées."));
        return donneesLocales.getDate();
    }

}
