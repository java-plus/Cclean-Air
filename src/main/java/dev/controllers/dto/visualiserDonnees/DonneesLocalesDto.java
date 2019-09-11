package dev.controllers.dto.visualiserDonnees;

import java.time.ZonedDateTime;

/**
 * @author Cécile
 * Classe qui retourne les données nécessaires pour l'affichage des données locales
 */
public class DonneesLocalesDto {

    CommuneDtoVisualisation communeDtoVisualisation;

    //Alerte -> Voir comment récupérer si la commune est en alerte ou pas

    PolluantDtoVisualisation polluantDtoVisualisation;

    ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation;

    private ZonedDateTime date;

    public DonneesLocalesDto() {
    }

    public DonneesLocalesDto(CommuneDtoVisualisation communeDtoVisualisation, PolluantDtoVisualisation polluantDtoVisualisation, ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation, ZonedDateTime date) {
        this.communeDtoVisualisation = communeDtoVisualisation;
        this.polluantDtoVisualisation = polluantDtoVisualisation;
        this.conditionMeteoDtoVisualisation = conditionMeteoDtoVisualisation;
        this.date = date;
    }

    public CommuneDtoVisualisation getCommuneDtoVisualisation() {
        return communeDtoVisualisation;
    }

    public void setCommuneDtoVisualisation(CommuneDtoVisualisation communeDtoVisualisation) {
        this.communeDtoVisualisation = communeDtoVisualisation;
    }

    public PolluantDtoVisualisation getPolluantDtoVisualisation() {
        return polluantDtoVisualisation;
    }

    public void setPolluantDtoVisualisation(PolluantDtoVisualisation polluantDtoVisualisation) {
        this.polluantDtoVisualisation = polluantDtoVisualisation;
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
