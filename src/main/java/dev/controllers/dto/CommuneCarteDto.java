package dev.controllers.dto;

/**
 * @author Guillaume Informations destinés à l'affichage des communes sur la
 *         carte coté front
 */
public class CommuneCarteDto {

	private String codeINSEE;
	private String codePostal;
	private String nomCommune;
	private Double latitude;
	private Double longitude;
	private NiveauAlerteDto alerte;

	public CommuneCarteDto(String codeINSEE, String codePostal, String nomCommune, Double latitude, Double longitude,
			NiveauAlerteDto alerte) {
		super();
		this.codeINSEE = codeINSEE;
		this.codePostal = codePostal;
		this.nomCommune = nomCommune;
		this.latitude = latitude;
		this.longitude = longitude;
		this.alerte = alerte;
	}

	public CommuneCarteDto() {
		super();
	}

	@Override
	public String toString() {
		return "CommuneCarteDto [codeINSEE=" + codeINSEE + ", codePostal=" + codePostal + ", nomCommune=" + nomCommune
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", alerte=" + alerte + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alerte == null) ? 0 : alerte.hashCode());
		result = prime * result + ((codeINSEE == null) ? 0 : codeINSEE.hashCode());
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		CommuneCarteDto other = (CommuneCarteDto) obj;
		if (alerte == null) {
			if (other.alerte != null)
				return false;
		} else if (!alerte.equals(other.alerte))
			return false;
		if (codeINSEE == null) {
			if (other.codeINSEE != null)
				return false;
		} else if (!codeINSEE.equals(other.codeINSEE))
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (nomCommune == null) {
			if (other.nomCommune != null)
				return false;
		} else if (!nomCommune.equals(other.nomCommune))
			return false;
		return true;
	}

	/**
	 * @return the codeINSEE
	 */
	public String getCodeINSEE() {
		return codeINSEE;
	}

	/**
	 * @param codeINSEE the codeINSEE to set
	 */
	public void setCodeINSEE(String codeINSEE) {
		this.codeINSEE = codeINSEE;
	}

	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
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
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the alerte
	 */
	public NiveauAlerteDto getAlerte() {
		return alerte;
	}

	/**
	 * @param alerte the alerte to set
	 */
	public void setAlerte(NiveauAlerteDto alerte) {
		this.alerte = alerte;
	}

}
