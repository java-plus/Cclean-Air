package dev.controllers.dto;

/**
 * @author Guillaume Classe utilisée pour l'affichage de la vue concernant la
 *         liste des indicateurs
 *
 */
public class CommuneIndicateurDto {

	/**
	 * Commune concernée par l'indicateur
	 */
	private String commune;

	public CommuneIndicateurDto(String commune) {
		super();
		this.commune = commune;
	}

	public CommuneIndicateurDto() {
		super();
	}

	@Override
	public String toString() {
		return "CommuneIndicateurDto [commune=" + commune + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commune == null) ? 0 : commune.hashCode());
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
		CommuneIndicateurDto other = (CommuneIndicateurDto) obj;
		if (commune == null) {
			if (other.commune != null)
				return false;
		} else if (!commune.equals(other.commune))
			return false;
		return true;
	}

	/**
	 * @return the commune
	 */
	public String getCommune() {
		return commune;
	}

	/**
	 * @param commune the commune to set
	 */
	public void setCommune(String commune) {
		this.commune = commune;
	}

}
