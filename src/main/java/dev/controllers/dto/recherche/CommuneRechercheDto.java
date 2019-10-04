package dev.controllers.dto.recherche;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import dev.controllers.dto.NiveauAlerteDto;

/**
 * @author Guillaume
 *
 */
public class CommuneRechercheDto {

	/**
	 * Code Insee de la commune
	 */
	private String codeInsee;

	/**
	 * Nom de la commune sur lequel se base la recherche
	 */
	private String nomCommune;
	/**
	 * Nom du polluant sur lequel se basse la recherche
	 */
	private String polluant;
	/**
	 * Date de la donnée
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	/**
	 * Heure de la donnée
	 */

	private Integer heure;

	/**
	 * Niveau d'alerte de la commune
	 */
	private NiveauAlerteDto alerte;

	public CommuneRechercheDto(String codeInsee, String nomCommune, String polluant, LocalDate date, Integer heure,
			NiveauAlerteDto alerte) {
		super();
		this.codeInsee = codeInsee;
		this.nomCommune = nomCommune;
		this.polluant = polluant;
		this.date = date;
		this.heure = heure;
		this.alerte = alerte;
	}

	public CommuneRechercheDto() {
		super();
	}

	@Override
	public String toString() {
		return "CommuneRechercheDto [codeInsee=" + codeInsee + ", nomCommune=" + nomCommune + ", polluant=" + polluant
				+ ", date=" + date + ", heure=" + heure + ", alerte=" + alerte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alerte == null) ? 0 : alerte.hashCode());
		result = prime * result + ((codeInsee == null) ? 0 : codeInsee.hashCode());
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
		if (alerte == null) {
			if (other.alerte != null)
				return false;
		} else if (!alerte.equals(other.alerte))
			return false;
		if (codeInsee == null) {
			if (other.codeInsee != null)
				return false;
		} else if (!codeInsee.equals(other.codeInsee))
			return false;
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
	 * @return the codeInsee
	 */
	public String getCodeInsee() {
		return codeInsee;
	}

	/**
	 * @param codeInsee the codeInsee to set
	 */
	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
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
	public Integer getHeure() {
		return heure;
	}

	/**
	 * @param heure the heure to set
	 */
	public void setHeure(Integer heure) {
		this.heure = heure;
	}

	/**
	 * @return the alerte
	 */
	public NiveauAlerteDto getAlerte() {
		return alerte;
	}

	/**
	 * @param alerte the alerte to set
	 */
	public void setAlerte(NiveauAlerteDto alerte) {
		this.alerte = alerte;
	}

}
