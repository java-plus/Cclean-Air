package dev.controllers.dto.recherche.resultat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import dev.controllers.dto.NiveauAlerteDto;

/**
 * @author Guillaume Classe utilisée pour l'affichage des données détaillées
 *         pour une commune
 *
 */
public class CommuneDto {

	private String codeInsee;
	private String nom;
	private LocalDate date;
	private LocalTime heure;
	private List<PolluantDto> polluants;
	private Long nbHabitants;
	private ConditionMeteoDto meteo;
	private NiveauAlerteDto niveauAlerte;

	public CommuneDto(String codeInsee, String nom, LocalDate date, LocalTime heure, List<PolluantDto> polluants,
			Long nbHabitants, ConditionMeteoDto meteo, NiveauAlerteDto niveauAlerte) {
		super();
		this.codeInsee = codeInsee;
		this.nom = nom;
		this.date = date;
		this.heure = heure;
		this.polluants = polluants;
		this.nbHabitants = nbHabitants;
		this.meteo = meteo;
		this.niveauAlerte = niveauAlerte;
	}

	public CommuneDto() {
		super();
	}

	@Override
	public String toString() {
		return "CommuneDto [codeInsee=" + codeInsee + ", nom=" + nom + ", date=" + date + ", heure=" + heure
				+ ", polluants=" + polluants + ", nbHabitants=" + nbHabitants + ", meteo=" + meteo + ", niveauAlerte="
				+ niveauAlerte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeInsee == null) ? 0 : codeInsee.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((heure == null) ? 0 : heure.hashCode());
		result = prime * result + ((meteo == null) ? 0 : meteo.hashCode());
		result = prime * result + ((nbHabitants == null) ? 0 : nbHabitants.hashCode());
		result = prime * result + ((niveauAlerte == null) ? 0 : niveauAlerte.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((polluants == null) ? 0 : polluants.hashCode());
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
		CommuneDto other = (CommuneDto) obj;
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
		if (meteo == null) {
			if (other.meteo != null)
				return false;
		} else if (!meteo.equals(other.meteo))
			return false;
		if (nbHabitants == null) {
			if (other.nbHabitants != null)
				return false;
		} else if (!nbHabitants.equals(other.nbHabitants))
			return false;
		if (niveauAlerte == null) {
			if (other.niveauAlerte != null)
				return false;
		} else if (!niveauAlerte.equals(other.niveauAlerte))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (polluants == null) {
			if (other.polluants != null)
				return false;
		} else if (!polluants.equals(other.polluants))
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
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

	/**
	 * @return the polluants
	 */
	public List<PolluantDto> getPolluants() {
		return polluants;
	}

	/**
	 * @param polluants the polluants to set
	 */
	public void setPolluants(List<PolluantDto> polluants) {
		this.polluants = polluants;
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

	/**
	 * @return the meteo
	 */
	public ConditionMeteoDto getMeteo() {
		return meteo;
	}

	/**
	 * @param meteo the meteo to set
	 */
	public void setMeteo(ConditionMeteoDto meteo) {
		this.meteo = meteo;
	}

	/**
	 * @return the niveauAlerte
	 */
	public NiveauAlerteDto getNiveauAlerte() {
		return niveauAlerte;
	}

	/**
	 * @param niveauAlerte the niveauAlerte to set
	 */
	public void setNiveauAlerte(NiveauAlerteDto niveauAlerte) {
		this.niveauAlerte = niveauAlerte;
	}

}
