package dev.controllers.dto.recherche.resultat;

/**
 * @author Guillaume
 *
 */
public class ConditionMeteoDto {

	private Double humidite;
	private Double ensoleillement;
	private Double temperature;

	public ConditionMeteoDto(Double humidite, Double ensoleillement, Double temperature) {
		super();
		this.humidite = humidite;
		this.ensoleillement = ensoleillement;
		this.temperature = temperature;
	}

	public ConditionMeteoDto() {
		super();
	}

	@Override
	public String toString() {
		return "ConditionMeteoDto [humidite=" + humidite + ", ensoleillement=" + ensoleillement + ", temperature="
				+ temperature + "]";
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
		ConditionMeteoDto other = (ConditionMeteoDto) obj;
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

	/**
	 * @return the humidite
	 */
	public Double getHumidite() {
		return humidite;
	}

	/**
	 * @param humidite the humidite to set
	 */
	public void setHumidite(Double humidite) {
		this.humidite = humidite;
	}

	/**
	 * @return the ensoleillement
	 */
	public Double getEnsoleillement() {
		return ensoleillement;
	}

	/**
	 * @param ensoleillement the ensoleillement to set
	 */
	public void setEnsoleillement(Double ensoleillement) {
		this.ensoleillement = ensoleillement;
	}

	/**
	 * @return the temperature
	 */
	public Double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

}
