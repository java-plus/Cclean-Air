package dev.controllers.dto.visualiserDonnees;

/**
 * @author Cécile
 * Objet DTO qui permet de récupérer l'ensoleillement, la température et la pluviométrie
 * Utilisé pour DonneesLocalesDto
 */
public class ConditionMeteoDtoVisualisation {

    /**
     * Taux d'ensoleillement
     */
    private Double ensoleillement;

    /**
     * température en degré celsius
     */
    private Double temperature;

    /**
     * taux d'humidite
     */
    private Double humidite;

    public ConditionMeteoDtoVisualisation() {
    }

    public ConditionMeteoDtoVisualisation(Double ensoleillement, Double temperature, Double humidite) {
        this.ensoleillement = ensoleillement;
        this.temperature = temperature;
        this.humidite = humidite;
    }

    public Double getEnsoleillement() {
        return ensoleillement;
    }

    public void setEnsoleillement(Double ensoleillement) {
        this.ensoleillement = ensoleillement;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidite() {
        return humidite;
    }

    public void setHumidite(Double humidite) {
        this.humidite = humidite;
    }
}
