package dev.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Guillaume Classe traitant les données météorologiques récupérés
 *         depuis l'API
 *
 */
@Entity
@Table(name = "CONDITION_METEO")
public class ConditionMeteo implements Serializable {

	private static final long serialVersionUID = -5085875379534690977L;

	/**
	 * Identifiant unique de la condition météorologique, clé primaire de la table
	 * associée en base
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_id")
	private Integer id;
	/**
	 * Taux d'ensoleillement
	 */
	@Column(name = "con_ensoleillement")
	private Double ensoleillement;
	/**
	 * Température en degrés celsius
	 */
	@Column(name = "con_temperature")
	private Double temperature;
	/**
	 * taux de pluviometrie
	 */
	@Column(name = "con_pluviometrie")
	private Double pluviometrie;
	/**
	 * Indicateur temporel de la donnée
	 */
	@Column(name = "con_date")
	private ZonedDateTime date;
	@OneToMany(mappedBy = "conditionMeteo")
	@Column(name = "con_donnees_locales")
	private List<DonneesLocales> donnesLocales;

	public ConditionMeteo(Double ensoleillement, Double temperature, Double pluviometrie, ZonedDateTime date,
			List<DonneesLocales> donnesLocales) {
		super();
		this.ensoleillement = ensoleillement;
		this.temperature = temperature;
		this.pluviometrie = pluviometrie;
		this.date = date;
		this.donnesLocales = donnesLocales;
	}

	public ConditionMeteo() {
		super();
	}

	@Override
	public String toString() {
		return "ConditionMeteo [id=" + id + ", ensoleillement=" + ensoleillement + ", temperature=" + temperature
				+ ", pluviometrie=" + pluviometrie + ", date=" + date + ", donnesLocales=" + donnesLocales + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((donnesLocales == null) ? 0 : donnesLocales.hashCode());
		result = prime * result + ((ensoleillement == null) ? 0 : ensoleillement.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pluviometrie == null) ? 0 : pluviometrie.hashCode());
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
		ConditionMeteo other = (ConditionMeteo) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (donnesLocales == null) {
			if (other.donnesLocales != null)
				return false;
		} else if (!donnesLocales.equals(other.donnesLocales))
			return false;
		if (ensoleillement == null) {
			if (other.ensoleillement != null)
				return false;
		} else if (!ensoleillement.equals(other.ensoleillement))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pluviometrie == null) {
			if (other.pluviometrie != null)
				return false;
		} else if (!pluviometrie.equals(other.pluviometrie))
			return false;
		if (temperature == null) {
			if (other.temperature != null)
				return false;
		} else if (!temperature.equals(other.temperature))
			return false;
		return true;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * @return the pluviometrie
	 */
	public Double getPluviometrie() {
		return pluviometrie;
	}

	/**
	 * @param pluviometrie the pluviometrie to set
	 */
	public void setPluviometrie(Double pluviometrie) {
		this.pluviometrie = pluviometrie;
	}

	/**
	 * @return the date
	 */
	public ZonedDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	/**
	 * @return the donnesLocales
	 */
	public List<DonneesLocales> getDonnesLocales() {
		return donnesLocales;
	}

	/**
	 * @param donnesLocales the donnesLocales to set
	 */
	public void setDonnesLocales(List<DonneesLocales> donnesLocales) {
		this.donnesLocales = donnesLocales;
	}

}