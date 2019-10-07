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

	public DonneesLocalesDto(CommuneDtoVisualisation communeDtoVisualisation,
			List<PolluantDtoVisualisation> listePolluantDtoVisualisation,
			ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation, ZonedDateTime date) {
		this.commune = communeDtoVisualisation;
		this.listePolluants = listePolluantDtoVisualisation;
		this.conditionMeteo = conditionMeteoDtoVisualisation;
		this.date = date;
	}

	public CommuneDtoVisualisation getCommuneDtoVisualisation() {
		return commune;
	}

	public void setCommuneDtoVisualisation(CommuneDtoVisualisation communeDtoVisualisation) {
		this.commune = communeDtoVisualisation;
	}

	public List<PolluantDtoVisualisation> getListePolluants() {
		return listePolluants;
	}

	public void setListePolluants(List<PolluantDtoVisualisation> listePolluantDtoVisualisation) {
		this.listePolluants = listePolluantDtoVisualisation;
	}

	public ConditionMeteoDtoVisualisation getConditionMeteoDtoVisualisation() {
		return conditionMeteo;
	}

	public void setConditionMeteoDtoVisualisation(ConditionMeteoDtoVisualisation conditionMeteoDtoVisualisation) {
		this.conditionMeteo = conditionMeteoDtoVisualisation;
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

}
