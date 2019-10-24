package dev.controllers.dto;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotBlank;

/**
 * @author Cécile
 *
 *         Objet DTO qui reprend les champs pour la recherche d'un historique
 *
 */

public class DonneesLocalesRecherchees {

	@NotBlank
	private ZonedDateTime dateDebut;
	@NotBlank
	private ZonedDateTime dateFin;
	@NotBlank
	private String polluant;

	public DonneesLocalesRecherchees() {
	}

	public DonneesLocalesRecherchees(@NotBlank ZonedDateTime dateDebut, @NotBlank ZonedDateTime dateFin,
			@NotBlank String polluant) {
		this.dateDebut = dateDebut;

		this.dateFin = dateFin;

		this.polluant = polluant;
	}

	public ZonedDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(ZonedDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public ZonedDateTime getDateFin() {
		return dateFin;
	}

	public void setDateFin(ZonedDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public String getPolluant() {
		return polluant;
	}

	public void setPolluant(String polluant) {
		this.polluant = polluant;
	}
}
