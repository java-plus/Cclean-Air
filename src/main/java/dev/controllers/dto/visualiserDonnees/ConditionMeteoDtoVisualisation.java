package dev.controllers.dto.visualiserDonnees;

/**
 * @author Cécile Objet DTO qui permet de récupérer l'ensoleillement, la
 *         température et la pluviométrie Utilisé pour DonneesLocalesDto
 */
public class ConditionMeteoDtoVisualisation {

	/**
	 * Taux d'ensoleillement
	 */
	private Double ensoleillement;

	/**
	 * température en degré celsius
	 */
	private Double temperature;

	/**
	 * taux d'humidite
	 */
	private Double humidite;

	public ConditionMeteoDtoVisualisation() {
	}

	public ConditionMeteoDtoVisualisation(Double ensoleillement, Double temperature, Double humidite) {
		this.ensoleillement = ensoleillement;
		this.temperature = temperature;
		this.humidite = humidite;
	}

	public Double getEnsoleillement() {
		return ensoleillement;
	}

	public void setEnsoleillement(Double ensoleillement) {
		this.ensoleillement = ensoleillement;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidite() {
		return humidite;
	}

	public void setHumidite(Double humidite) {
		this.humidite = humidite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ensoleillement == null) ? 0 : ensoleillement.hashCode());
		result = prime * result + ((humidite == null) ? 0 : humidite.hashCode());
		result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
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
		ConditionMeteoDtoVisualisation other = (ConditionMeteoDtoVisualisation) obj;
		if (ensoleillement == null) {
			if (other.ensoleillement != null)
				return false;
		} else if (!ensoleillement.equals(other.ensoleillement))
			return false;
		if (humidite == null) {
			if (other.humidite != null)
				return false;
		} else if (!humidite.equals(other.humidite))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}

}
