package dev.controllers.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Guillaume
 *
 */
public class CommuneRechercheDto {

	private String nomCommune;
	private String polluant;
	private LocalDate date;
	private LocalTime heure;

	public CommuneRechercheDto(String nomCommune, String polluant, LocalDate date, LocalTime heure) {
		super();
		this.nomCommune = nomCommune;
		this.polluant = polluant;
		this.date = date;
		this.heure = heure;
	}

	public CommuneRechercheDto() {
		super();
	}

	@Override
	public String toString() {
		return "CommuneRechercheDto [nomCommune=" + nomCommune + ", polluant=" + polluant + ", date=" + date
				+ ", heure=" + heure + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((heure == null) ? 0 : heure.hashCode());
		result = prime * result + ((nomCommune == null) ? 0 : nomCommune.hashCode());
		result = prime * result + ((polluant == null) ? 0 : polluant.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommuneRechercheDto other = (CommuneRechercheDto) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (heure == null) {
			if (other.heure != null)
				return false;
		} else if (!heure.equals(other.heure))
			return false;
		if (nomCommune == null) {
			if (other.nomCommune != null)
				return false;
		} else if (!nomCommune.equals(other.nomCommune))
			return false;
		if (polluant == null) {
			if (other.polluant != null)
				return false;
		} else if (!polluant.equals(other.polluant))
			return false;
		return true;
	}

	/**
	 * @return the nomCommune
	 */
	public String getNomCommune() {
		return nomCommune;
	}

	/**
	 * @param nomCommune the nomCommune to set
	 */
	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	/**
	 * @return the polluant
	 */
	public String getPolluant() {
		return polluant;
	}

	/**
	 * @param polluant the polluant to set
	 */
	public void setPolluant(String polluant) {
		this.polluant = polluant;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the heure
	 */
	public LocalTime getHeure() {
		return heure;
	}

	/**
	 * @param heure the heure to set
	 */
	public void setHeure(LocalTime heure) {
		this.heure = heure;
	}

}
