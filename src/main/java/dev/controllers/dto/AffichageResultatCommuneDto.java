package dev.controllers.dto;

import java.time.ZonedDateTime;

import dev.controllers.dto.visualiserDonnees.DonneesLocalesDto;

/**
 * @author Guillaume Utilisé pour 'laffichage des résultats d'une commune depuis
 *         la recherche.
 *
 */
public class AffichageResultatCommuneDto {

	private String nomCommune;
	private ZonedDateTime date;
	private Boolean alerte;
	private DonneesLocalesDto donnees;
	private Long nbHabitants;

	public AffichageResultatCommuneDto(String nomCommune, ZonedDateTime date, Boolean alerte, DonneesLocalesDto donnees,
			Long nbHabitants) {
		super();
		this.nomCommune = nomCommune;
		this.date = date;
		this.alerte = alerte;
		this.donnees = donnees;
		this.nbHabitants = nbHabitants;
	}

	public AffichageResultatCommuneDto() {
		super();
	}

	@Override
	public String toString() {
		return "AffichageResultatCommuneDto [nomCommune=" + nomCommune + ", date=" + date + ", alerte=" + alerte
				+ ", donnees=" + donnees + ", nbHabitants=" + nbHabitants + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alerte == null) ? 0 : alerte.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((donnees == null) ? 0 : donnees.hashCode());
		result = prime * result + ((nbHabitants == null) ? 0 : nbHabitants.hashCode());
		result = prime * result + ((nomCommune == null) ? 0 : nomCommune.hashCode());
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
		AffichageResultatCommuneDto other = (AffichageResultatCommuneDto) obj;
		if (alerte == null) {
			if (other.alerte != null)
				return false;
		} else if (!alerte.equals(other.alerte))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (donnees == null) {
			if (other.donnees != null)
				return false;
		} else if (!donnees.equals(other.donnees))
			return false;
		if (nbHabitants == null) {
			if (other.nbHabitants != null)
				return false;
		} else if (!nbHabitants.equals(other.nbHabitants))
			return false;
		if (nomCommune == null) {
			if (other.nomCommune != null)
				return false;
		} else if (!nomCommune.equals(other.nomCommune))
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
	 * @return the date
	 */
	public ZonedDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	/**
	 * @return the alerte
	 */
	public Boolean getAlerte() {
		return alerte;
	}

	/**
	 * @param alerte the alerte to set
	 */
	public void setAlerte(Boolean alerte) {
		this.alerte = alerte;
	}

	/**
	 * @return the donnees
	 */
	public DonneesLocalesDto getDonnees() {
		return donnees;
	}

	/**
	 * @param donnees the donnees to set
	 */
	public void setDonnees(DonneesLocalesDto donnees) {
		this.donnees = donnees;
	}

	/**
	 * @return the nbHabitants
	 */
	public Long getNbHabitants() {
		return nbHabitants;
	}

	/**
	 * @param nbHabitants the nbHabitants to set
	 */
	public void setNbHabitants(Long nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

}
