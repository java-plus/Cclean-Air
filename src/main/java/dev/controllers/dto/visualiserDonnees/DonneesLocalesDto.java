package dev.controllers.dto.visualiserDonnees;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Cécile
 * Classe qui retourne les données nécessaires pour l'affichage des données locales
 */
public class DonneesLocalesDto {

    /**
     * Objet dto qui reprend le nom et le nombre d'habitant de la commune
     */
   private CommuneDtoVisualisation communeDtoVisualisation;

    //Alerte -> Voir comment récupérer si la commune est en alerte ou pas
    /**
     * liste d'objet dto qui reprend le nom, l'unité et la valeur d'un polluant
     */
   private List<PolluantDtoVisualisation> listePolluantDtoVisualisation;
    /**
     * objet dto qui reprend le tx d'ensoleilleemnt, l'humidité et la température
     */
   private ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation;
    /**
     * date
     */
    private ZonedDateTime date;

    public DonneesLocalesDto() {
    }

    public DonneesLocalesDto(CommuneDtoVisualisation communeDtoVisualisation, List<PolluantDtoVisualisation> listePolluantDtoVisualisation, ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation, ZonedDateTime date) {
        this.communeDtoVisualisation = communeDtoVisualisation;
        this.listePolluantDtoVisualisation = listePolluantDtoVisualisation;
        this.conditionMeteoDtoVisualisation = conditionMeteoDtoVisualisation;
        this.date = date;
    }

    public CommuneDtoVisualisation getCommuneDtoVisualisation() {
        return communeDtoVisualisation;
    }

    public void setCommuneDtoVisualisation(CommuneDtoVisualisation communeDtoVisualisation) {
        this.communeDtoVisualisation = communeDtoVisualisation;
    }

    public List<PolluantDtoVisualisation> getListePolluantDtoVisualisation() {
        return listePolluantDtoVisualisation;
    }

    public void setListePolluantDtoVisualisation(List<PolluantDtoVisualisation> listePolluantDtoVisualisation) {
        this.listePolluantDtoVisualisation = listePolluantDtoVisualisation;
    }

    public ConditionMeteoDtoVisualisation getConditionMeteoDtoVisualisation() {
        return conditionMeteoDtoVisualisation;
    }

    public void setConditionMeteoDtoVisualisation(ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation) {
        this.conditionMeteoDtoVisualisation = conditionMeteoDtoVisualisation;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
