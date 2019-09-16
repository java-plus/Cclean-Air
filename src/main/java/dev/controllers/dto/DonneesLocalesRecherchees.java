package dev.controllers.dto;

import dev.entities.Polluant;
import org.apache.tomcat.jni.Poll;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Cécile
 *
 * Objet DTO qui reprend les champs pour la recherche d'un historique
 *
 */

public class DonneesLocalesRecherchees {

    @NotBlank
    private LocalDate dateDebut;
    @NotBlank
    private LocalTime heureDebut;
    @NotBlank
    private LocalDate dateFin;
    @NotBlank
    private LocalTime heureFin;
    @NotBlank
    private String polluant;

    public DonneesLocalesRecherchees() {
    }

    public DonneesLocalesRecherchees(@NotBlank LocalDate dateDebut, @NotBlank LocalTime heureDebut, @NotBlank LocalDate dateFin, @NotBlank LocalTime heureFin, @NotBlank String polluant) {
        this.dateDebut = dateDebut;
        this.heureDebut = heureDebut;
        this.dateFin = dateFin;
        this.heureFin = heureFin;
        this.polluant = polluant;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public String getPolluant() {
        return polluant;
    }

    public void setPolluant(String polluant) {
        this.polluant = polluant;
    }
}
