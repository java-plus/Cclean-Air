package dev.services;

import dev.controllers.dto.CommuneDto;
import dev.entities.Commune;
import dev.exceptions.CommuneInvalideException;
import dev.repositories.ICommuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe regroupant les services d'une commune géographique.
 */
@Service
public class CommuneService {

    private final Logger LOGGER = LoggerFactory.getLogger(CommuneService.class);

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

}
