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
 * Classe regroupant les méthode de service liées aux données météorologiques.
 */
@Service
public class ConditionMeteoService {

	private final Logger LOGGER = LoggerFactory.getLogger(ConditionMeteoService.class);

	@Value("${url.meteo_api}")
	private String URL_API_METEO;

	@Value("${key.meteo_api}")
	private String KEY_API_METEO;

	private IConditionMeteoRepository conditionMeteoRepository;

	@Autowired
	public ConditionMeteoService(IConditionMeteoRepository conditionMeteoRepository) {
		this.conditionMeteoRepository = conditionMeteoRepository;
	}

	/**
	 * Méthode récupérant les données météorologiques d'une commune à partir de
	 * l'API météo.
	 * 
	 * @param commune : [CommuneDto] la commune pour laquelle récupérer les données
	 *                météorologiques.
	 * @return [ConditionMeteo] un objet contenant les données météorologiques.
	 * @throws ConditionMeteoException : exception lancée en cas de problème lors de
	 *                                 la récupération.
	 */
	public ConditionMeteo recupererConditionMeteoCommuneDeApi(CommuneDto commune) throws ConditionMeteoException {

		LOGGER.info("recupererConditionMeteoCommune() lancé");

		StringBuilder sbUrlApiWeather = new StringBuilder(URL_API_METEO);
		sbUrlApiWeather.append("?lat=");
		sbUrlApiWeather.append(commune.getLatitude());
		sbUrlApiWeather.append("&lon=");
		sbUrlApiWeather.append(commune.getLongitude());
		sbUrlApiWeather.append("&lang=fr&units=metric&APPID=");
		sbUrlApiWeather.append(KEY_API_METEO);
		sbUrlApiWeather.append("&units=metric");
		LOGGER.info("url de la requête = " + sbUrlApiWeather);

		try {
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(sbUrlApiWeather.toString(), String.class);
			LOGGER.info("recuperation donnees = " + result);
			Double ensoleillement = 100 - Double.parseDouble(
					result.substring(result.indexOf("clouds\":{\"all\":") + 15, result.indexOf("},\"dt\":")));
			Double temperature = Double
					.parseDouble(result.substring(result.indexOf("temp\":") + 6, result.indexOf(",\"pressure")));
			Double humidite = Double
					.parseDouble(result.substring(result.indexOf("humidity\":") + 10, result.indexOf(",\"temp_min")));
			return new ConditionMeteo(ensoleillement, temperature, humidite, ZonedDateTime.now(), null);
		} catch (RuntimeException e) {
			throw new ConditionMeteoException("ERREUR : la récupération des données de l'API météo a échouché. \n" + e);
		}
	}

	/**
	 * Méthode sauvegardant une condition méteo dans la base de donnée.
	 * 
	 * @param conditionMeteo [ConditionMeteo] la condition météo à sauvegarder
	 * @return [ConditionMeteo] la condition météo qui a été sauvegarder.
	 */
	public ConditionMeteo sauvegarderConditionMeteo(ConditionMeteo conditionMeteo) {
		LOGGER.info("sauvegarderConditionMeteo() lancé");
		LOGGER.info("conditionMeteo = " + conditionMeteo);
		return conditionMeteoRepository.save(conditionMeteo);
	}

	/**
	 * Méthode permettant de supprimer de la base de données les données
	 * météorologiques antérieures à la date-heure indiquée.
	 * 
	 * @param dateExpiration : [ZonedDateTime] date-heure au-delà de laquelle les
	 *                       données sont à supprimer
	 */
	public void purgerConditionMeteo(ZonedDateTime dateExpiration) {
		LOGGER.info("purgerConditionMeteo()");
		conditionMeteoRepository.deleteAllExpiredSince(dateExpiration);
	}
}
