package dev.utils;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component
@Transactional
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
