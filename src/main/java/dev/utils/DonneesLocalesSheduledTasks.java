package dev.utils;

import java.time.ZonedDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.entities.QualiteAir;
import dev.exceptions.DonneesLocalesSheduledTasksException;
import dev.services.ConditionMeteoService;
import dev.services.DonneesLocalesService;
import dev.services.PolluantService;
import dev.services.QualiteAirService;

/**
 * Classe gérant le lancement automatique des méthodes nécessaires à la
 * génération et à la sauvegarde des données locales.
 */
@Component
public class DonneesLocalesSheduledTasks {

	private Logger LOGGER = LoggerFactory.getLogger(DonneesLocalesSheduledTasks.class);

	private DonneesLocalesService donneesLocalesService;
	private ConditionMeteoService conditionMeteoService;
	private QualiteAirService qualiteAirService;
	private PolluantService polluantService;

	@Autowired
	public DonneesLocalesSheduledTasks(DonneesLocalesService donneesLocalesService,
			ConditionMeteoService conditionMeteoService, QualiteAirService qualiteAirService,
			PolluantService polluantService) {
		this.donneesLocalesService = donneesLocalesService;
		this.conditionMeteoService = conditionMeteoService;
		this.qualiteAirService = qualiteAirService;
		this.polluantService = polluantService;
	}

	/**
	 * Méthode lançant périodiquement (à chaque heure) la méthode permettant de
	 * générer et de sauvegarder toutes les données locales.
	 */
	@Scheduled(cron = "0 0 * * * *")
	public void recupererEtSauvegarderDonneesLocales() {
		LOGGER.info("lancement de recupererEtSauvegarderDonneesLocales()");
		donneesLocalesService.genererEtsauvegarderDonneesLocales(
				ZonedDateTime.now().minusHours(1).withMinute(0).withSecond(0).withNano(0), null);
	}

	/**
	 * Méthode lançant périodiquement (chaque jour à 3h00 du matin) la méthode
	 * permettant de purger les données de plus de 7 jours (données locales, données
	 * météorologiques et données de pollution).
	 */
	@Scheduled(cron = "0 0 3 * * *")
	public void purgerDonneesLocales() {
		LOGGER.info("lancement de recupererEtSauvegarderDonneesLocales()");
		ZonedDateTime dateExpiration = donneesLocalesService.recupererDateHeureDeDerniereDonneesLocales().minusDays(7);
		LOGGER.info("date d'expiration des données, à partir de laquelle il y aura suppression des données locales "
				+ "antérieures : " + dateExpiration);
		try {
			donneesLocalesService.purgerDonneesLocales(dateExpiration);

			LOGGER.info("purge des données météorologiques...");
			conditionMeteoService.purgerConditionMeteo(dateExpiration);

			LOGGER.info("purge des données de qualité d'air...");
			List<QualiteAir> listeQualiteAir = qualiteAirService.recupererLesQualitesAirAvantDate(dateExpiration);
			for (QualiteAir q : listeQualiteAir) {
				polluantService.purgerPolluant(q);
			}
			qualiteAirService.purgerQualiteAir(dateExpiration);
		} catch (RuntimeException e) {
			LOGGER.error("DonneesLocalesSheduledTasksException levée : " + e);
			throw new DonneesLocalesSheduledTasksException(
					"ERREUR : La purge automatique de la base de données a " + "rencontré une erreur. \n" + e);
		}

	}
}
