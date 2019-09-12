package dev.controllers.dto.visualiserDonnees;

public class PolluantDtoVisualisation {

    private String nom;

    private Double Valeur;

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
