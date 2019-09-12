package dev.services;

import dev.controllers.dto.visualiserDonnees.*;
import dev.entities.*;
import dev.exceptions.CommuneInvalideException;
import dev.repositories.*;
import dev.repositories.IConditionMeteoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Classe regroupant les services d'une commune géographique.
 */
@Service
public class CommuneService {

    private final Logger LOGGER = LoggerFactory.getLogger(CommuneService.class);

    private IDonneesLocalesRepository donneesLocalesRepository;

    private ICommuneRepository communeRepository;

   private IQualiteAirRepository qualiteAirRepository;

   private IConditionMeteoRepository conditionMeteoRepository;

    private IPolluantRepository polluantRepository;

    @Autowired
    public CommuneService(IDonneesLocalesRepository donneesLocalesRepository, ICommuneRepository communeRepository, IQualiteAirRepository qualiteAirRepository, IConditionMeteoRepository conditionMeteoRepository, IPolluantRepository polluantRepository) {
        this.donneesLocalesRepository = donneesLocalesRepository;
        this.communeRepository = communeRepository;
        this.qualiteAirRepository = qualiteAirRepository;
        this.conditionMeteoRepository = conditionMeteoRepository;
        this.polluantRepository = polluantRepository;
    }

    public Boolean isCommuneExistante(String nomCommune) {
        return communeRepository.findByNomIgnoreCase(nomCommune).isPresent();
    }

    // TODO: compléter
    public Commune recupererCommune(String nom) {
        return new Commune();
    }

    /**
     * Méthode qui permet de créer les données locales à partir des différentes entités
     * @param codeInsee
     * @return
     */
    public DonneesLocalesDto creerDonneesLocalesCommune(String codeInsee) {

        //récupération de la commune
        Optional<Commune> commune = communeRepository.findByCodeInsee(codeInsee);

        if(!commune.isPresent()){
            throw new CommuneInvalideException("La commune n'existe pas");
        }

        //Récupération de l'objet communeDtovisualisation
        CommuneDtoVisualisation communeDtoVisualisation = new CommuneDtoVisualisation(commune.get().getNom(), commune.get().getNbHabitants());

        //Récupération de la dernière date d'enregistrements pour la commune
        ZonedDateTime date = donneesLocalesRepository.findByCommune(commune);
        //Récupération des données locales
        DonneesLocales donneesLocales = donneesLocalesRepository.findByCommuneAndDate(commune, date);

        //Récupéartion des id qualiteAir et conditionMeteo
        Integer qualiteAirId = null;
        Integer conditionMeteoId = null;
        if (donneesLocales != null) {
            qualiteAirId = donneesLocales.getQualiteAir().getId();
            conditionMeteoId = donneesLocales.getConditionMeteo().getId();
        }

        //Récupération qualitéAir
        Optional<QualiteAir> qualiteAir = null;
        if (qualiteAirId != null) {
            qualiteAir = qualiteAirRepository.findById(qualiteAirId);
        }

        //Récupération ConditionMeteo et création du conditionMeteoDtoVisualisation
        Optional<ConditionMeteo> conditionMeteo = conditionMeteoRepository.findById(conditionMeteoId);
        ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation = new ConditionMeteoDtoVisualisation(conditionMeteo.get().getEnsoleillement(), conditionMeteo.get().getTemperature(), conditionMeteo.get().getHumidite());

        //Création de l'objet DonneesLocalesDto
        DonneesLocalesDto donneesLocalesDto = new DonneesLocalesDto();
        donneesLocalesDto.setDate(date);
        donneesLocalesDto.setCommuneDtoVisualisation(communeDtoVisualisation);
        if (qualiteAir.isPresent()) {
            List<PolluantDtoVisualisation> listePolluant = polluantRepository.findByQualiteAir(qualiteAir.get());
            donneesLocalesDto.setListePolluantDtoVisualisation(listePolluant);
        } else {
            donneesLocalesDto.setListePolluantDtoVisualisation(null);
        }
        donneesLocalesDto.setConditionMeteoDtoVisualisation(conditionMeteoDtoVisualisation);

        LOGGER.info("création des données locales à afficher /classe CommuneService");

        return donneesLocalesDto;
    }

}
