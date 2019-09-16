package dev.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Cécile
 * Objet Dto qui sert pour modifier le profil
 */
public class ProfilModificationPost {
    /**
     * nom de l'utilisateur
     */
    private String nom;
    /**
     * prénom de l'utilisateur
     */
    private String prenom;
    /**
     * mail de l'utilisateur
     */
    @Email(message ="Le format de l'email est incorrect")
    private String email;
    /**
     * commune de l'utilisateur
     */
    private String commune;
    /**
     * Acceptation ou non des alertes
     */
    private Boolean alerte;

    /**
     * Liste des alertes par indicateru
     */
    private List<CommuneIndicateurDto> listeIndicateurs ;

    /**
     * mot de passe actuel de l'utilisateur
     */
    private String motDePasseActuel;
    /**
     * nouveau mot de passe de l'utilisateur
     */
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Le mot de passe ne respecte pas les règles de sécurité")
    private String motDePasseNouveau;
    /**
     * controle du nouveau mot de passe de l'utilisateur
     */

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Le mot de passe ne respecte pas les règles de sécurité")
    private String getMotDePasseNouveauValidation;

    public ProfilModificationPost(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, @NotBlank String commune, @NotBlank Boolean alerte, @NotBlank List<CommuneIndicateurDto> listeIndicateurs, @NotBlank String motDePasseActuel, @NotBlank String motDePasseNouveau, @NotBlank String getMotDePasseNouveauValidation) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.commune = commune;
        this.alerte = alerte;
        this.listeIndicateurs = listeIndicateurs;
        this.motDePasseActuel = motDePasseActuel;
        this.motDePasseNouveau = motDePasseNouveau;
        this.getMotDePasseNouveauValidation = getMotDePasseNouveauValidation;
    }

    public ProfilModificationPost() {
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

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public Boolean getAlerte() {
        return alerte;
    }

    public void setAlerte(Boolean alerte) {
        this.alerte = alerte;
    }

    public String getMotDePasseActuel() {
        return motDePasseActuel;
    }

    public void setMotDePasseActuel(String motDePasseActuel) {
        this.motDePasseActuel = motDePasseActuel;
    }

    public String getMotDePasseNouveau() {
        return motDePasseNouveau;
    }

    public void setMotDePasseNouveau(String motDePasseNouveau) {
        this.motDePasseNouveau = motDePasseNouveau;
    }

    public String getGetMotDePasseNouveauValidation() {
        return getMotDePasseNouveauValidation;
    }

    public void setGetMotDePasseNouveauValidation(String getMotDePasseNouveauValidation) {
        this.getMotDePasseNouveauValidation = getMotDePasseNouveauValidation;
    }

    public List<CommuneIndicateurDto> getListeIndicateurs() {
        return listeIndicateurs;
    }

    public void setListeIndicateurs(List<CommuneIndicateurDto> listeIndicateurs) {
        this.listeIndicateurs = listeIndicateurs;
    }
}
