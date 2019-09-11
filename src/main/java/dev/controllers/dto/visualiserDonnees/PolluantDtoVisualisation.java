package dev.controllers.dto.visualiserDonnees;

/**
 * @author Cécile
 * Classe qui récupère le nom, l'unité et la valeur d'un polluant
 * Utilisé pour afficher avec donneesLocalesDto
 *
 */
public class PolluantDtoVisualisation {

    /**
     * Nom du polluant
     */
    private String nom;

    /**
     * Unité de mesure du polluant
     */
    private String unite;

    /**
     * Mesure chiffrée du polluant
     */
    private Double valeur;

    public PolluantDtoVisualisation(String nom, String unite, Double valeur) {
        this.nom = nom;
        this.unite = unite;
        this.valeur = valeur;
    }

    public PolluantDtoVisualisation() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
