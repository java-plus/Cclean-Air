package dev.controllers.dto;

import dev.entities.Commune;
import dev.entities.Utilisateur;

/**
 * @author Guillaume Classe utilisé pour l'affichage de la confirmation de
 *         création d'un indicateur
 *
 */
public class IndicateurDto {

	private Utilisateur utilisateur;
	private Commune commune;

	public IndicateurDto(Utilisateur utilisateur, Commune commune) {
		super();
		this.utilisateur = utilisateur;
		this.commune = commune;
	}

	public IndicateurDto() {
		super();
	}

	@Override
	public String toString() {
		return "IndicateurDto [utilisateur=" + utilisateur + ", commune=" + commune + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commune == null) ? 0 : commune.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
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
		IndicateurDto other = (IndicateurDto) obj;
		if (commune == null) {
			if (other.commune != null)
				return false;
		} else if (!commune.equals(other.commune))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}

	/**
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return the commune
	 */
	public Commune getCommune() {
		return commune;
	}

	/**
	 * @param commune the commune to set
	 */
	public void setCommune(Commune commune) {
		this.commune = commune;
	}

}
