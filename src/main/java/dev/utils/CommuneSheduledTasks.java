package dev.utils;

import dev.services.CommuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Classe gérant le lancement automatique des méthodes de la classe
 * CommuneService.
 */
@Component
public class CommuneSheduledTasks {

	private final Logger LOGGER = LoggerFactory.getLogger(CommuneSheduledTasks.class);

	private CommuneService communeService;

	@Autowired
	public CommuneSheduledTasks(CommuneService communeService) {
		this.communeService = communeService;
	}

	/**
	 * Méthode lançant périodiquement la méthode de récupération de toutes les
	 * communes de l'API (tous les 15 jours).
	 */
	@Scheduled(initialDelay = 1000, fixedRate = 1296000000)
	public void recupererDonneesApiCommunesEtSauvegarder() {
		LOGGER.info("lancement de recupererDonneesApiCommunesEtSauvegarder()");
		communeService.recupererCommunesDeApi();
		LOGGER.info("nombre de communes dans la base : " + communeService.recupererToutesLesCommunesDeLaBase().size());
	}
}
