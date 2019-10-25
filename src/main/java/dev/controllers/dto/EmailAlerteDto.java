package dev.controllers.dto;

/**
 * Dto représentant un email d'alerte envoyable par un administrateur.
 */
public class EmailAlerteDto {

    private String communeInsee;
    private String corpsMsg;
    private String objet;

    public EmailAlerteDto() {
    }

    public EmailAlerteDto(String communeInsee, String corpsMsg, String objet) {
        this.communeInsee = communeInsee;
        this.corpsMsg = corpsMsg;
        this.objet = objet;
    }

    public String getCommuneInsee() {
        return communeInsee;
    }

    public void setCommuneInsee(String communeInsee) {
        this.communeInsee = communeInsee;
    }

    public String getCorpsMsg() {
        return corpsMsg;
    }

    public void setCorpsMsg(String corpsMsg) {
        this.corpsMsg = corpsMsg;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }
}
