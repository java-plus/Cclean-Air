package dev.controllers.dto;

import java.util.List;

public class CommuneDtoGet {
    private String nom;
    private Integer code;
    private List<String> codesPostaux;
    private CentreDto centre;
    private String codeRegion;
    private Long population;

    public CommuneDtoGet() {
    }

    public CommuneDtoGet(String nom, Integer code, List<String> codesPostaux, CentreDto centre, String codeRegion,
                         Long population) {
        this.nom = nom;
        this.code = code;
        this.codesPostaux = codesPostaux;
        this.centre = centre;
        this.codeRegion = codeRegion;
        this.population = population;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<String> getCodesPostaux() {
        return codesPostaux;
    }

    public void setCodesPostaux(List<String> codesPostaux) {
        this.codesPostaux = codesPostaux;
    }

    public CentreDto getCentre() {
        return centre;
    }

    public void setCentre(CentreDto centre) {
        this.centre = centre;
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "CommuneDtoGet{" +
                "nom='" + nom + '\'' +
                ", code=" + code +
                ", codesPostaux=" + codesPostaux +
                ", centre=" + centre +
                ", codeRegion='" + codeRegion + '\'' +
                ", population=" + population +
                '}';
    }
}
