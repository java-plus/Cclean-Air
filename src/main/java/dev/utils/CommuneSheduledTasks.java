package dev.utils;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.services.CommuneService;

@Component
@Transactional
public class CommuneSheduledTasks {

	private final Logger LOGGER = LoggerFactory.getLogger(CommuneSheduledTasks.class);

	private CommuneService communeService;

	@Autowired
	public CommuneSheduledTasks(CommuneService communeService) {
		this.communeService = communeService;
	}

	@Scheduled(initialDelay = 1000, fixedRate = 1296000000)
	public void recupererDonneesApiCommunesEtSauvegarder() {
		LOGGER.info("lancement de recupererDonneesApiCommunesEtSauvegarder()");

		// List<CommuneDto> communes = communeService.recupererCommunesDeApi();
		communeService.recupererCommunesDeApi();

		/*
		 * for (CommuneDto c : communes) { ConditionMeteo conditionMeteo =
		 * conditionMeteoService.recupererConditionMeteoCommune(c);
		 * conditionMeteoService.sauvegarderConditionMeteo(conditionMeteo); }
		 */

	}
}
