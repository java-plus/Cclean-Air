package dev.services;

import dev.controllers.dto.visualiserDonnees.CommuneDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;
import dev.entities.Commune;
import dev.entities.DonneesLocales;
import dev.repositories.ICommuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

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

    // TODO: compléter
    public Commune recupererCommune(String nom) {
        return new Commune();
    }

    public DonneesLocalesDto creerDonneesLocalesCommune(String codeInsee, ZonedDateTime date){

        DonneesLocalesDto donneesLocalesDto = new DonneesLocalesDto();

        Commune commune = new Commune();

      Optional <CommuneDtoVisualisation> communeDtoVisualisation = communeRepository.findByCodeInsee(codeInsee);

    if(communeDtoVisualisation.isPresent()){
        donneesLocalesDto.setCommuneDtoVisualisation(communeDtoVisualisation.get());
    }


        return donneesLocalesDto;
    }

}
