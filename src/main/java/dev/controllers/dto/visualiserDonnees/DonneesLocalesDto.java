package dev.controllers.dto.visualiserDonnees;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Cécile Classe qui retourne les données nécessaires pour l'affichage
 *         des données locales
 */
public class DonneesLocalesDto {

	// Alerte -> Voir comment récupérer si la commune est en alerte ou pas

	/**
	 * Objet dto qui reprend le nom et le nombre d'habitant de la commune
	 */
	private CommuneDtoVisualisation commune;

	// Alerte -> Voir comment récupérer si la commune est en alerte ou pas
	/**
	 * liste d'objet dto qui reprend le nom, l'unité et la valeur d'un polluant
	 */
	private List<PolluantDtoVisualisation> listePolluants;
	/**
	 * objet dto qui reprend le tx d'ensoleilleemnt, l'humidité et la température
	 */
	private ConditionMeteoDtoVisualisation conditionMeteo;
	/**
	 * date
	 */
	private ZonedDateTime date;

	public DonneesLocalesDto() {
	}

	/**
	 * Constructeur
	 * 
	 * @param commune
	 * @param listePolluants
	 * @param conditionMeteo
	 * @param date
	 */
	public DonneesLocalesDto(CommuneDtoVisualisation commune, List<PolluantDtoVisualisation> listePolluants,
			ConditionMeteoDtoVisualisation conditionMeteo, ZonedDateTime date) {
		super();
		this.commune = commune;
		this.listePolluants = listePolluants;
		this.conditionMeteo = conditionMeteo;
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commune == null) ? 0 : commune.hashCode());
		result = prime * result + ((conditionMeteo == null) ? 0 : conditionMeteo.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((listePolluants == null) ? 0 : listePolluants.hashCode());
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
		DonneesLocalesDto other = (DonneesLocalesDto) obj;
		if (commune == null) {
			if (other.commune != null)
				return false;
		} else if (!commune.equals(other.commune))
			return false;
		if (conditionMeteo == null) {
			if (other.conditionMeteo != null)
				return false;
		} else if (!conditionMeteo.equals(other.conditionMeteo))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (listePolluants == null) {
			if (other.listePolluants != null)
				return false;
		} else if (!listePolluants.equals(other.listePolluants))
			return false;
		return true;
	}

	/**
	 * Getter
	 * 
	 * @return the commune
	 */
	public CommuneDtoVisualisation getCommune() {
		return commune;
	}

	/**
	 * Setter
	 * 
	 * @param commune the commune to set
	 */
	public void setCommune(CommuneDtoVisualisation commune) {
		this.commune = commune;
	}

	/**
	 * Getter
	 * 
	 * @return the listePolluants
	 */
	public List<PolluantDtoVisualisation> getListePolluants() {
		return listePolluants;
	}

	/**
	 * Setter
	 * 
	 * @param listePolluants the listePolluants to set
	 */
	public void setListePolluants(List<PolluantDtoVisualisation> listePolluants) {
		this.listePolluants = listePolluants;
	}

	/**
	 * Getter
	 * 
	 * @return the conditionMeteo
	 */
	public ConditionMeteoDtoVisualisation getConditionMeteo() {
		return conditionMeteo;
	}

	/**
	 * Setter
	 * 
	 * @param conditionMeteo the conditionMeteo to set
	 */
	public void setConditionMeteo(ConditionMeteoDtoVisualisation conditionMeteo) {
		this.conditionMeteo = conditionMeteo;
	}

	/**
	 * Getter
	 * 
	 * @return the date
	 */
	public ZonedDateTime getDate() {
		return date;
	}

	/**
	 * Setter
	 * 
	 * @param date the date to set
	 */
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

}
