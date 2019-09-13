package dev.controllers.dto;

/**
 * @author Guillaume Classe utilisé pour l'affichage de la confirmation de
 *         création d'un indicateur
 *
 */
public class IndicateurDto {

	private String mailUtilisateur;
	private String nomCommune;
	private Boolean alerte;

	public IndicateurDto() {
		super();
	}

	public IndicateurDto(String mailUtilisateur, String nomCommune, Boolean alerte) {
		super();
		this.mailUtilisateur = mailUtilisateur;
		this.nomCommune = nomCommune;
		this.alerte = alerte;
	}

	@Override
	public String toString() {
		return "IndicateurDto [mailUtilisateur=" + mailUtilisateur + ", nomCommune=" + nomCommune + ", alerte=" + alerte
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alerte == null) ? 0 : alerte.hashCode());
		result = prime * result + ((mailUtilisateur == null) ? 0 : mailUtilisateur.hashCode());
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
		IndicateurDto other = (IndicateurDto) obj;
		if (alerte == null) {
			if (other.alerte != null)
				return false;
		} else if (!alerte.equals(other.alerte))
			return false;
		if (mailUtilisateur == null) {
			if (other.mailUtilisateur != null)
				return false;
		} else if (!mailUtilisateur.equals(other.mailUtilisateur))
			return false;
		if (nomCommune == null) {
			if (other.nomCommune != null)
				return false;
		} else if (!nomCommune.equals(other.nomCommune))
			return false;
		return true;
	}

	/**
	 * @return the mailUtilisateur
	 */
	public String getMailUtilisateur() {
		return mailUtilisateur;
	}

	/**
	 * @param mailUtilisateur the mailUtilisateur to set
	 */
	public void setMailUtilisateur(String mailUtilisateur) {
		this.mailUtilisateur = mailUtilisateur;
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
