package dev.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe représentant une commune géographique.
 */
@Entity
@Table(name = "COMMUNE")
public class Commune implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private Integer id;
    @Column(name = "com_nom")
    private String nom;
    @Column(name = "com_nb_habitants")
    private Long nbHabitants;
    @Column(name = "com_code_insee")
    private String codeInsee;
    @Column(name = "com_latitude")
    private Double latitude;
    @Column(name = "com_longitude")
    private Double longitude;

    /**
     * Constructeur
     */
    public Commune() {
    }

    /**
     * Constructeur
     * @param nom : [String] nom de la commune
     * @param nbHabitants : [Long] nombre d'habitants dans la commune
     * @param codeInsee : [String] code insee de la commune
     * @param latitude : [Double] latitude géographique de la commune
     * @param longitude : [Double] longitude géographique de la commune
     */
    public Commune(String nom, Long nbHabitants, String codeInsee, Double latitude, Double longitude) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
        this.codeInsee = codeInsee;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Commune{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", nbHabitants=" + nbHabitants +
                ", codeInsee='" + codeInsee + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
