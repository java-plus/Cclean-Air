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

	private Boolean alerte;

	public CommuneIndicateurDto(String commune, Boolean alerte) {
		super();
		this.commune = commune;
		this.alerte = alerte;
	}

	public CommuneIndicateurDto() {
		super();
	}

	@Override
	public String toString() {
		return "CommuneIndicateurDto [commune=" + commune + ", alerte=" + alerte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alerte == null) ? 0 : alerte.hashCode());
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
		if (alerte == null) {
			if (other.alerte != null)
				return false;
		} else if (!alerte.equals(other.alerte))
			return false;
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

}
