package dev.controllers.dto;

/**
 * Classe représentant un dto d'alerte pollution.
 */
public class CommuneNiveauAlerteDto {
    private String nomCommune;
    private String nomPolluant;
    private Double valeur;

    /**
     * Constructeur
     */
    public CommuneNiveauAlerteDto() {
    }

    /**
     * Constructeur
     * @param nomCommune : nom de la commune où il y a une alerte
     * @param nomPolluant : nom du polluant anormalement élevé
     * @param valeur : valeur de la mesure du polluant
     */
    public CommuneNiveauAlerteDto(String nomCommune, String nomPolluant,
                                  Double valeur) {
        this.nomCommune = nomCommune;
        this.nomPolluant = nomPolluant;
        this.valeur = valeur;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public String getNomPolluant() {
        return nomPolluant;
    }

    public void setNomPolluant(String nomPolluant) {
        this.nomPolluant = nomPolluant;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
