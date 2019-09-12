package dev.services;

import dev.controllers.dto.CommuneDto;
import dev.controllers.dto.CommuneDtoGet;
import dev.entities.Commune;
import dev.entities.ConditionMeteo;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.ConditionMeteoException;
import dev.repositories.ICommuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Classe regroupant les services d'une commune géographique.
 */
@Service
public class CommuneService {

    private final Logger LOGGER = LoggerFactory.getLogger(CommuneService.class);

    @Value("${url.communes_api}")
    private String URL_API_COMMUNES;

    private ICommuneRepository communeRepository;

    @Autowired
    public CommuneService(ICommuneRepository communeRepository) {
        this.communeRepository = communeRepository;
    }

    public Boolean isCommuneExistante(String nomCommune) {
        return communeRepository.findByNomIgnoreCase(nomCommune).isPresent();
    }

    public Commune recupererCommune(String commune) {
        return communeRepository.findByNomIgnoreCase(commune).orElseThrow(() -> new CommuneInvalideException("ERREUR " +
                ": Commune inexistante dans la base de données."));
    }

    public List<CommuneDto> recupererToutesLesCommunesDto() {
        return communeRepository.findAllWithCodeDenomination();
    }

    // FIXME: méthode
    public ConditionMeteo recupererCommunesDeApi() {

        LOGGER.info("recupererConditionMeteoCommune() lancé");

        LOGGER.info("url de l'api = " + URL_API_COMMUNES);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<CommuneDtoGet>> response = restTemplate.exchange(
                    URL_API_COMMUNES,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CommuneDtoGet>>(){});
            List<CommuneDtoGet> communes = response.getBody();

            LOGGER.info(communes.toString());
            return null;
        } catch (Exception e) {
            throw new ConditionMeteoException("ERREUR : la récupération des données de l'API météo a échouché. \n" + e);
        }
    }
}
