package dev.controllers.dto;

import dev.entities.Polluant;
import org.apache.tomcat.jni.Poll;

import java.time.LocalDate;
import java.time.LocalTime;

public class DonneesLocalesRecherchees {

    private LocalDate dateDebut;
    private LocalTime heureDebut;
    private LocalDate dateFin;
    private LocalTime heureFin;

    private String polluant;

    public DonneesLocalesRecherchees() {
    }

    public DonneesLocalesRecherchees(LocalDate dateDebut, LocalTime heureDebut, LocalDate dateFin, LocalTime heureFin, String polluant) {
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
