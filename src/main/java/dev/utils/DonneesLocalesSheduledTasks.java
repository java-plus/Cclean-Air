package dev.utils;

import dev.services.DonneesLocalesService;
import dev.services.QualiteAirService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Classe gérant le lancement automatique des méthodes nécessaires à la génération et à la sauvegarde des données
 * locales.
 */
@Component
public class DonneesLocalesSheduledTasks {

    private Logger LOGGER = LoggerFactory.getLogger(DonneesLocalesSheduledTasks.class);

    private QualiteAirService qualiteAirService;
    private DonneesLocalesService donneesLocalesService;

    @Autowired
    public DonneesLocalesSheduledTasks(QualiteAirService qualiteAirService,
                                       DonneesLocalesService donneesLocalesService) {
        this.qualiteAirService = qualiteAirService;
        this.donneesLocalesService = donneesLocalesService;
    }

    @Scheduled(cron="0 0 * * * *")
    public void recupererEtSauvegarderDonneesLocales() {
        LOGGER.info("lancement de recupererEtSauvegarderDonneesLocales()");
        donneesLocalesService.sauvegarderDonneesLocalesHeureCourranteMoinsUn();

    }
}
