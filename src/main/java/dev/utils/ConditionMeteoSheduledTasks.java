package dev.utils;

import dev.controllers.dto.CommuneDto;
import dev.entities.ConditionMeteo;
import dev.services.CommuneService;
import dev.services.ConditionMeteoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConditionMeteoSheduledTasks {

//    private final Logger LOGGER = LoggerFactory.getLogger(ConditionMeteoSheduledTasks.class);
//
//    private ConditionMeteoService conditionMeteoService;
//    private CommuneService communeService;
//
//    @Autowired
//    public ConditionMeteoSheduledTasks(ConditionMeteoService conditionMeteoService, CommuneService communeService) {
//        this.conditionMeteoService = conditionMeteoService;
//        this.communeService = communeService;
//    }
//
//    @Scheduled(cron="0 * * * * *")
//    public void recupererDonneesApiMeteoEtSauvegarder() {
//        LOGGER.info("lancement de recupererDonneesApiMeteoEtSauvegarder()");
//        List<CommuneDto> communes = communeService.recupererToutesLesCommunesDto();
//        for (CommuneDto c : communes) {
//            ConditionMeteo conditionMeteo = conditionMeteoService.recupererConditionMeteoCommune(c);
//            conditionMeteoService.sauvegarderConditionMeteo(conditionMeteo);
//        }
//
//    }

}
