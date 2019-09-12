package dev.services;

import dev.controllers.dto.visualiserDonnees.*;
import dev.entities.Commune;
import dev.entities.Polluant;
import dev.entities.QualiteAir;
import dev.repositories.ICommuneRepository;
import dev.repositories.IConditionMeteoRepository;
import dev.repositories.IPolluantRepository;
import dev.repositories.IQualiteAirRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe regroupant les services d'une commune géographique.
 */
@Service
public class CommuneService {

    private final Logger LOGGER = LoggerFactory.getLogger(CommuneService.class);

    @Autowired
    private ICommuneRepository communeRepository;

    @Autowired
    private IQualiteAirRepository qualiteAirRepository;

    @Autowired
    private IConditionMeteoRepository conditionMeteoRepository;

    @Autowired
    private IPolluantRepository polluantRepository;

//    @Autowired
//    public CommuneService(ICommuneRepository communeRepository) {
//        this.communeRepository = communeRepository;
//    }


    public Boolean isCommuneExistante(String nomCommune) {
        return communeRepository.findByNomIgnoreCase(nomCommune).isPresent();
    }

    // TODO: compléter
    public Commune recupererCommune(String nom) {
        return new Commune();
    }

    public DonneesLocalesDto creerDonneesLocalesCommune(String codeInsee, ZonedDateTime date) {

        LOGGER.info("Je passe dans la classe servie creer donnees locales commune");

        //Création de l'objet DonneesLocalesDto
        DonneesLocalesDto donneesLocalesDto = new DonneesLocalesDto();


        //Création de l'objet communeDtoVisaliation pour remplir donneesLocalesDto
        Optional<CommuneDtoVisualisation> communeDtoVisualisation = communeRepository.findByCodeInsee(codeInsee);

        if (communeDtoVisualisation.isPresent()) {
            donneesLocalesDto.setCommuneDtoVisualisation(communeDtoVisualisation.get());
        } else {
            donneesLocalesDto.setCommuneDtoVisualisation(null);
        }

        LOGGER.info("commune dto : " + donneesLocalesDto.getCommuneDtoVisualisation());

        //modification de  l'heure pour avoir l'heure de la dernière recherche et ajout à donneesLocalesDto
        date = date.withMinute(0);
        date = date.withSecond(0);
        date = date.withNano(0);

        donneesLocalesDto.setDate(date);

        LOGGER.info("date : " + donneesLocalesDto.getDate());


        //Récupération de la qualiteAir avec la date pour retrouver les polluants

        Optional<QualiteAir> qualiteAir = qualiteAirRepository.findByDate(date);

        //Création d'une liste de Polluants pour afficher dans donneesLocalesDto

        if (qualiteAir.isPresent()){
            List<PolluantDtoVisualisation> listePolluant = polluantRepository.findByQualiteAir(qualiteAir.get());
            donneesLocalesDto.setListePolluantDtoVisualisation(listePolluant);
        } else {
            donneesLocalesDto.setListePolluantDtoVisualisation(null);
        }


        LOGGER.info("liste polluant : " + donneesLocalesDto.getListePolluantDtoVisualisation());

        //Création de l'objet conditionMetoDtoVisualisation pour remplir donneesLocalesDto

        Optional<ConditionMeteoDtoVisualisation> conditionMeteoDtoVisualisation = conditionMeteoRepository.findByDate(date);

        if(conditionMeteoDtoVisualisation.isPresent()){
            donneesLocalesDto.setConditionMeteoDtoVisualisation(conditionMeteoDtoVisualisation.get());
        } else  {
            donneesLocalesDto.setConditionMeteoDtoVisualisation(null);
        }

        LOGGER.info("meteo : " + donneesLocalesDto.getConditionMeteoDtoVisualisation());

        return donneesLocalesDto;
    }

}
