package dev.services;

import dev.controllers.dto.CommuneDto;
import dev.entities.ConditionMeteo;
import dev.exceptions.ConditionMeteoException;
import dev.repositories.IConditionMeteoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;

/**
 * Classe regroupant les services liés aux données météorologiques.
 */
@Service
public class ConditionMeteoService {

    private final Logger LOGGER = LoggerFactory.getLogger(ConditionMeteoService.class);

    @Value("${url.meteo_api}")
    private String URL_API_METEO;

    private IConditionMeteoRepository conditionMeteoRepository;

    @Autowired
    public ConditionMeteoService(IConditionMeteoRepository conditionMeteoRepository) {
        this.conditionMeteoRepository = conditionMeteoRepository;
    }

    public ConditionMeteo recupererConditionMeteoCommune(CommuneDto commune) throws ConditionMeteoException {

        LOGGER.info("recupererConditionMeteoCommune() lancé");

        StringBuilder sbUrlApiWeather = new StringBuilder(URL_API_METEO);
        sbUrlApiWeather.append("?lat=");
        sbUrlApiWeather.append(commune.getLatitude());
        sbUrlApiWeather.append("&lon=");
        sbUrlApiWeather.append(commune.getLongitude());
        sbUrlApiWeather.append("&lang=fr&units=metric&APPID=633e2a135fc012a55447e2b1f366972d&units=metric");

        LOGGER.info("url de la requête = " + sbUrlApiWeather);

        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(sbUrlApiWeather.toString(), String.class);
            LOGGER.info("recuperation donnees = " + result.toString());
            Double ensoleillement = 100 - Double.parseDouble(result.substring(result.indexOf("clouds\":{\"all\":")+15,
                    result.indexOf("},\"dt\":")));
            Double temperature = Double.parseDouble(result.substring(result.indexOf("temp\":")+6, result.indexOf(
                    ",\"pressure")));
            Double humidite
                    = Double.parseDouble(result.substring(result.indexOf("humidity\":")+10, result.indexOf(
                    ",\"temp_min")));
            return new ConditionMeteo(ensoleillement, temperature, humidite,
                    ZonedDateTime.now(), null);
        } catch (Exception e) {
            throw new ConditionMeteoException("ERREUR : la récupération des données de l'API météo a échouché. \n" + e);
        }
    }

    public ConditionMeteo sauvegarderConditionMeteo(ConditionMeteo conditionMeteo) {
        return conditionMeteoRepository.save(conditionMeteo);
    }

}
