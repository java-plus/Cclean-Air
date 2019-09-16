package dev.services;

import dev.controllers.dto.PolluantDtoApi;
import dev.entities.Polluant;
import dev.exceptions.PolluantInvalideException;
import dev.repositories.IPolluantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolluantService {

    private final Logger LOGGER = LoggerFactory.getLogger(PolluantService.class);

    @Value("${url.pollution_api}")
    private String URL_API_POLLUTION;

    private IPolluantRepository polluantRepository;

    @Autowired
    public PolluantService(IPolluantRepository polluantRepository) {
        this.polluantRepository = polluantRepository;
    }

    public void sauvegarderPolluant(List<Polluant> polluants) {
        LOGGER.info("sauvegarderPolluant() lancé");
        for (Polluant p: polluants) {
            polluantRepository.save(p);
        }

    }

    public List<Polluant> recupererPolluantsDeApi() {
        LOGGER.info("recupererPolluantsDeApi() lancé");

        String urlPolluants = URL_API_POLLUTION + "/polluants";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<PolluantDtoApi>> response = restTemplate.exchange(
                    urlPolluants, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<PolluantDtoApi>>(){});
            List<PolluantDtoApi> polluantsDto = response.getBody();
            LOGGER.info("polluants récupérés : " + polluantsDto);
            List<Polluant> polluants = new ArrayList<>();
            for (PolluantDtoApi p : polluantsDto) {
                polluants.add(new Polluant(p.getId(), p.getNom()));
            }
            return polluants;

        } catch (RuntimeException e) {
            throw new PolluantInvalideException("ERREUR : la récupération de l'ensemble des polluants de l'API " +
                    "pollution a échouché. " +
                    "\n" + e);
        }
    }

}