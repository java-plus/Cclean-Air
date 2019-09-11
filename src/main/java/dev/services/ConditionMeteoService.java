package dev.services;

import dev.controllers.dto.CommuneDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConditionMeteoService {

    private final Logger LOGGER = LoggerFactory.getLogger(ConditionMeteoService.class);

    public void recupererConditionMeteoCommune(CommuneDto commune) {

        LOGGER.info("recupererConditionMeteoCommune() lancé");

        StringBuilder sbUrlApiWeather = new StringBuilder();
        sbUrlApiWeather.append("http://api.openweathermap.org/data/2.5/weather?lat=");
        sbUrlApiWeather.append(commune.getLatitude());
        sbUrlApiWeather.append("&lon=");
        sbUrlApiWeather.append(commune.getLongitude());
        sbUrlApiWeather.append("&lang=fr&units=metric&APPID=633e2a135fc012a55447e2b1f366972d&units=metric");

        LOGGER.info(sbUrlApiWeather.toString());

        // TODO: continuer ici
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(sbUrlApiWeather.toString(), String.class);
      /*  private Double ensoleillement;
        private Double temperature;
        private Double pluviometrie;
        private ZonedDateTime date;
        private List<DonneesLocales> donnesLocales;*/
      Double ensoleillement = Double.parseDouble(result.substring(result.indexOf("visibility\":") + 13, result.indexOf(
              ",\"wind")));
      Double temperature = Double.parseDouble(result.substring(result.indexOf("temp\":") + 7, result.indexOf(
              ",\"pressure")));
      /*Double*/



        LOGGER.info(result.toString());
    }

}
