package dev.controllers.dto;

/**
 * Classe dto représentant un polluant atmosphérique de l'api airpl.
 */
public class PolluantDtoApi {

    private String id;
    private String nom;
    private String code;
    private String famille;
    private Boolean principal;

    public PolluantDtoApi() {
    }

    public PolluantDtoApi(String id, String nom, String code, String famille, Boolean principal) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.famille = famille;
        this.principal = principal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    @Override
    public String toString() {
        return "PolluantDtoApi{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                ", famille='" + famille + '\'' +
                ", principal=" + principal +
                '}';
    }
}
