package dev.services;

import dev.controllers.dto.CommuneDto;
import dev.entities.Commune;
import dev.entities.ConditionMeteo;
import dev.entities.DonneesLocales;
import dev.entities.QualiteAir;
import dev.repositories.IDonneesLocalesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

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
     */
    public void sauvegarderDonneesLocalesHeureCourranteMoinsUn() {

        List<Commune> listeCommunes = communeService.recupererToutesLesCommunes();
        qualiteAirService.recupererEtSauvegarderQualiteAir();
        List<QualiteAir> liisteQualiteAir = qualiteAirService.recupererListeQualitesAirsSelonDate(qualiteAirService.recupererDateDerniereQualiteAirDeLaBase());

        for (Commune c : listeCommunes) {
            ConditionMeteo conditionMeteo =
                    conditionMeteoService.recupererConditionMeteoCommune(new CommuneDto(c.getNom(),
                            c.getNbHabitants(), c.getCodeInsee(), c.getLatitude(), c.getLongitude()));
            QualiteAir qualiteAir = qualiteAirService.recupererQualiteAirCorrespondantACoordonnees(liisteQualiteAir,
                    c.getLongitude(),
                    c.getLongitude());
            DonneesLocales donneesLocales =
                    new DonneesLocales(ZonedDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0),
                            c, conditionMeteo, qualiteAir);
            conditionMeteoService.sauvegarderConditionMeteo(conditionMeteo);
            donneesLocalesRepository.save(donneesLocales);
        }

    }

}
