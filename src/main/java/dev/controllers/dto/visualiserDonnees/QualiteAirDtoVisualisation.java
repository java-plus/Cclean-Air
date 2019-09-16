package dev.controllers.dto.visualiserDonnees;

import java.util.List;

import dev.entities.Polluant;

/**
 * @author Cécile Objet dto qui retourne la qualité de l'air
 */
public class QualiteAirDtoVisualisation {

	/**
	 * liste de polluants
	 */
	private List<Polluant> listePolluants;

	public QualiteAirDtoVisualisation() {
	}

	public QualiteAirDtoVisualisation(List<Polluant> listePolluants) {
		this.listePolluants = listePolluants;
	}

	public List<Polluant> getListePolluants() {
		return listePolluants;
	}

	public void setListePolluants(List<Polluant> listePolluants) {
		this.listePolluants = listePolluants;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		QualiteAirDtoVisualisation other = (QualiteAirDtoVisualisation) obj;
		if (listePolluants == null) {
			if (other.listePolluants != null)
				return false;
		} else if (!listePolluants.equals(other.listePolluants))
			return false;
		return true;
	}

}
