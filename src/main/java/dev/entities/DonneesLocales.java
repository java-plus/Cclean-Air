package dev.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Guillaume Classe regroupant un ensemble de données (météorologiques
 *         et qualité de l'air) concernant une commune spécifique
 *
 */
@Entity
@Table(name = "DONNEES_LOCALES")
public class DonneesLocales implements Serializable {

	private static final long serialVersionUID = -7244078628031609902L;

	/**
	 * Identifiant unique de la donnée, clé primaire de la table correspondante en
	 * base
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "don_id")
	private Integer id;
	/**
	 * Indicateur temporel de la donnée
	 */
	@Column(name = "don_date")
	private ZonedDateTime date;

	/**
	 * Commune concernée par les données
	 */
	@ManyToOne
	@JoinColumn(name = "don_commune")
	private Commune commune;

	/**
	 * Condition meteorologiques enregistrées associées à la commune
	 */
	@ManyToOne
	@JoinColumn(name = "don_condition_meteo")
	private ConditionMeteo conditionMeteo;

	public DonneesLocales() {

	}

	public DonneesLocales(ZonedDateTime date, Commune commune, ConditionMeteo conditionMeteo) {
		super();
		this.date = date;
		this.commune = commune;
		this.conditionMeteo = conditionMeteo;
	}

	@Override
	public String toString() {
		return "DonneesLocales [id=" + id + ", date=" + date + ", commune=" + commune + ", conditionMeteo="
				+ conditionMeteo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commune == null) ? 0 : commune.hashCode());
		result = prime * result + ((conditionMeteo == null) ? 0 : conditionMeteo.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DonneesLocales other = (DonneesLocales) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
	 * @return the commune
	 */
	public Commune getCommune() {
		return commune;
	}

	/**
	 * @param commune the commune to set
	 */
	public void setCommune(Commune commune) {
		this.commune = commune;
	}

	/**
	 * @return the conditionMeteo
	 */
	public ConditionMeteo getConditionMeteo() {
		return conditionMeteo;
	}

	/**
	 * @param conditionMeteo the conditionMeteo to set
	 */
	public void setConditionMeteo(ConditionMeteo conditionMeteo) {
		this.conditionMeteo = conditionMeteo;
	}

}
