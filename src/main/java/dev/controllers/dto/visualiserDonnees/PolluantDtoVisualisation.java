package dev.controllers.dto.visualiserDonnees;

/**
 * objet dto qui retourne les information utile pour l'affichage :
 * nom, valeur, unité
 */
public class PolluantDtoVisualisation {
    /**
     * nom du polluant
     */
    private String nom;
    /**
     * valeur du polluant
     */
    private Double Valeur;
    /**
     * untié du polluant
     */
    private  String unite;

    public PolluantDtoVisualisation() {
    }

    public PolluantDtoVisualisation(String nom, Double valeur, String unite) {
        this.nom = nom;
        Valeur = valeur;
        this.unite = unite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getValeur() {
        return Valeur;
    }

    public void setValeur(Double valeur) {
        Valeur = valeur;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
}
