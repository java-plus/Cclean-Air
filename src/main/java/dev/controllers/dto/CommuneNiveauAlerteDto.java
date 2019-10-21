package dev.controllers.dto;

/**
 * Classe représentant un dto d'alerte pollution.
 */
public class CommuneNiveauAlerteDto {
    private String nomCommune;
    private String nomPolluant;
    private Double valeur;
    private String codeInseeCommune;

    /**
     * Constructeur
     */
    public CommuneNiveauAlerteDto() {
    }

    /**
      Constructeur
     * @param nomCommune : nom de la commune où il y a une alerte
     * @param nomPolluant : nom du polluant anormalement élevé
     * @param valeur : valeur de la mesure du polluant
     * @param codeInseeCommune : code insee de la commune
     */
    public CommuneNiveauAlerteDto(String nomCommune, String nomPolluant,
                                  Double valeur, String codeInseeCommune) {
        this.nomCommune = nomCommune;
        this.nomPolluant = nomPolluant;
        this.valeur = valeur;
        this.codeInseeCommune = codeInseeCommune;
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

    public String getCodeInseeCommune() {
        return codeInseeCommune;
    }

    public void setCodeInseeCommune(String codeInseeCommune) {
        this.codeInseeCommune = codeInseeCommune;
    }
}
