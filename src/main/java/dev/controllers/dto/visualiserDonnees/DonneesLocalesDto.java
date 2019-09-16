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
	private CommuneDtoVisualisation communeDtoVisualisation;

	// Alerte -> Voir comment récupérer si la commune est en alerte ou pas
	/**
	 * liste d'objet dto qui reprend le nom, l'unité et la valeur d'un polluant
	 */
	private List<PolluantDtoVisualisation> listePolluantDtoVisualisation;
	/**
	 * objet dto qui reprend le tx d'ensoleilleemnt, l'humidité et la température
	 */
	private ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation;
	/**
	 * date
	 */
	private ZonedDateTime date;

	public DonneesLocalesDto() {
	}

	public DonneesLocalesDto(CommuneDtoVisualisation communeDtoVisualisation,
			List<PolluantDtoVisualisation> listePolluantDtoVisualisation,
			ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation, ZonedDateTime date) {
		this.communeDtoVisualisation = communeDtoVisualisation;
		this.listePolluantDtoVisualisation = listePolluantDtoVisualisation;
		this.conditionMeteoDtoVisualisation = conditionMeteoDtoVisualisation;
		this.date = date;
	}

	public CommuneDtoVisualisation getCommuneDtoVisualisation() {
		return communeDtoVisualisation;
	}

	public void setCommuneDtoVisualisation(CommuneDtoVisualisation communeDtoVisualisation) {
		this.communeDtoVisualisation = communeDtoVisualisation;
	}

	public List<PolluantDtoVisualisation> getListePolluantDtoVisualisation() {
		return listePolluantDtoVisualisation;
	}

	public void setListePolluantDtoVisualisation(List<PolluantDtoVisualisation> listePolluantDtoVisualisation) {
		this.listePolluantDtoVisualisation = listePolluantDtoVisualisation;
	}

	public ConditionMeteoDtoVisualisation getConditionMeteoDtoVisualisation() {
		return conditionMeteoDtoVisualisation;
	}

	public void setConditionMeteoDtoVisualisation(ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation) {
		this.conditionMeteoDtoVisualisation = conditionMeteoDtoVisualisation;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((communeDtoVisualisation == null) ? 0 : communeDtoVisualisation.hashCode());
		result = prime * result
				+ ((conditionMeteoDtoVisualisation == null) ? 0 : conditionMeteoDtoVisualisation.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((listePolluantDtoVisualisation == null) ? 0 : listePolluantDtoVisualisation.hashCode());
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
		if (communeDtoVisualisation == null) {
			if (other.communeDtoVisualisation != null)
				return false;
		} else if (!communeDtoVisualisation.equals(other.communeDtoVisualisation))
			return false;
		if (conditionMeteoDtoVisualisation == null) {
			if (other.conditionMeteoDtoVisualisation != null)
				return false;
		} else if (!conditionMeteoDtoVisualisation.equals(other.conditionMeteoDtoVisualisation))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (listePolluantDtoVisualisation == null) {
			if (other.listePolluantDtoVisualisation != null)
				return false;
		} else if (!listePolluantDtoVisualisation.equals(other.listePolluantDtoVisualisation))
			return false;
		return true;
	}

}
