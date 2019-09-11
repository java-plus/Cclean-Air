package dev.services;

import dev.controllers.dto.CommuneDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConditionMeteoServiceTest {

    @Autowired
    private CommuneService communeService;
    @Autowired
    private ConditionMeteoService conditionMeteoService;

    @Test
    public void test_recupererConditionMeteoCommune_pas_exception() {
        conditionMeteoService.recupererConditionMeteoCommune(new CommuneDto(communeService.recupererCommune(
                "Nantes")));
    }

}
