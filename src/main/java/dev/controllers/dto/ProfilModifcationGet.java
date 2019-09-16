package dev.controllers.dto;

import ch.qos.logback.core.util.SystemInfo;
import dev.entities.Commune;
import dev.entities.Indicateur;

import java.util.List;

public class ProfilModifcationGet {

    private String nom;
    private String prenom;
    private String email;
    private Boolean statutNofication;
    private String commune;
    private List<CommuneIndicateurDto> listeIndicateurs;

    public ProfilModifcationGet() {
    }

    public ProfilModifcationGet(String nom, String prenom, String email, Boolean statutNofication, String commune, List<CommuneIndicateurDto> listeIndicateurs) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.statutNofication = statutNofication;
        this.commune = commune;
        this.listeIndicateurs = listeIndicateurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatutNofication() {
        return statutNofication;
    }

    public void setStatutNofication(Boolean statutNofication) {
        this.statutNofication = statutNofication;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public List<CommuneIndicateurDto> getListeIndicateurs() {
        return listeIndicateurs;
    }

    public void setListeIndicateurs(List<CommuneIndicateurDto> listeIndicateurs) {
        this.listeIndicateurs = listeIndicateurs;
    }
}
