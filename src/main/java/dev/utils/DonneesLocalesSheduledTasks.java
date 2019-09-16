package dev.utils;

import dev.services.DonneesLocalesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * Classe gérant le lancement automatique des méthodes nécessaires à la génération et à la sauvegarde des données
 * locales.
 */
@Component
public class DonneesLocalesSheduledTasks {

    private Logger LOGGER = LoggerFactory.getLogger(DonneesLocalesSheduledTasks.class);

    private DonneesLocalesService donneesLocalesService;

    @Autowired
    public DonneesLocalesSheduledTasks(DonneesLocalesService donneesLocalesService) {
        this.donneesLocalesService = donneesLocalesService;
    }

    /**
     * Méthode lançant périodiquement (à chaque heure) la méthode permettant de générer et de sauvegarder toutes les
     * données locales.
     */
    // TODO: changer l'annotation Scheduled une fois que l'application sera déployée.
    // @Scheduled(cron="0 0 * * * *") : annotation à garder par la suite
    @Scheduled(initialDelay=5000, fixedRate=1296000000) // lancement au bout de 5s, après démarrage de l'appli
    public void recupererEtSauvegarderDonneesLocales() {
        LOGGER.info("lancement de recupererEtSauvegarderDonneesLocales()");
        donneesLocalesService.genererEtsauvegarderDonneesLocales(ZonedDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0), 10);

    }
}
