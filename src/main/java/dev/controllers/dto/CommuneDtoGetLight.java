package dev.controllers.dto;

/**
 * Classe représentant une commune. Contient que ses attributs principaux.
 */
public class CommuneDtoGetLight {

    private String nom;
    private Long nbHabitants;
    private String codeInsee;
    private Double latitude;
    private Double longitude;

    /**
     * Constructeur
     */
    public CommuneDtoGetLight() {
    }

    /**
     * Constructeur
     * @param nom : String
     * @param nbHabitants : Long
     * @param codeInsee : String
     * @param latitude : Double
     * @param longitude : Double
     */
    public CommuneDtoGetLight(String nom, Long nbHabitants,
                              String codeInsee, Double latitude,
                              Double longitude) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.codeInsee = codeInsee;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(Long nbHabitants) {
        this.nbHabitants = nbHabitants;
    }

    public String getCodeInsee() {
        return codeInsee;
    }

    public void setCodeInsee(String codeInsee) {
        this.codeInsee = codeInsee;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
