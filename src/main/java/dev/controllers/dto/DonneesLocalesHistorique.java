package dev.controllers.dto;

import dev.controllers.dto.visualiserDonnees.CommuneDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.PolluantDtoVisualisation;

import java.time.ZonedDateTime;

public class DonneesLocalesHistorique {

    private PolluantDtoVisualisation polluantDtoVisualisation;

    private CommuneDtoVisualisation communeDtoVisualisation;

    private ZonedDateTime date;

    public DonneesLocalesHistorique() {
    }

    public DonneesLocalesHistorique(PolluantDtoVisualisation polluantDtoVisualisation, CommuneDtoVisualisation communeDtoVisualisation, ZonedDateTime date) {
        this.polluantDtoVisualisation = polluantDtoVisualisation;
        this.communeDtoVisualisation = communeDtoVisualisation;
        this.date = date;
    }

    public PolluantDtoVisualisation getPolluantDtoVisualisation() {
        return polluantDtoVisualisation;
    }

    public void setPolluantDtoVisualisation(PolluantDtoVisualisation polluantDtoVisualisation) {
        this.polluantDtoVisualisation = polluantDtoVisualisation;
    }

    public CommuneDtoVisualisation getCommuneDtoVisualisation() {
        return communeDtoVisualisation;
    }

    public void setCommuneDtoVisualisation(CommuneDtoVisualisation communeDtoVisualisation) {
        this.communeDtoVisualisation = communeDtoVisualisation;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
