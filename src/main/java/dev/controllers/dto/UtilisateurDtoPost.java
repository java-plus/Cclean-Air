package dev.controllers.dto;

import dev.entities.Statut;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UtilisateurDtoPost {

    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    @NotBlank
    private String motDePasse;
    private Statut statut;
    @NotBlank
    private String nomCommune;
    @NotBlank
    private String codePostal;
    @NotNull
    private Boolean statutNotification;

    public UtilisateurDtoPost() {
    }

    public UtilisateurDtoPost(String nom, String prenom, String email, String motDePasse, Statut statut,
                              String nomCommune, String codePostal, Boolean statutNotification) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.statut = statut;
        this.nomCommune = nomCommune;
        this.codePostal = codePostal;
        this.statutNotification = statutNotification;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Boolean getStatutNotification() {
        return statutNotification;
    }

    public void setStatutNotification(Boolean statutNotification) {
        this.statutNotification = statutNotification;
    }
}
