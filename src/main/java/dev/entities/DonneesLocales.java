package dev.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

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
	 * Conditions meteorologiques enregistrées associées à la commune
	 */
	@ManyToOne
	@JoinColumn(name = "don_condition_meteo")
	private ConditionMeteo conditionMeteo;

	/**
	 * Données de qualités de l'air enregistrées associées à la commune
	 */
	@ManyToOne
	@JoinColumn(name = "don_qualite_air")
	private QualiteAir qualiteAir;

	public DonneesLocales() {

	}

	public DonneesLocales(ZonedDateTime date, Commune commune, ConditionMeteo conditionMeteo, QualiteAir qualiteAir) {
		super();
		this.date = date;
		this.commune = commune;
		this.conditionMeteo = conditionMeteo;
		this.qualiteAir = qualiteAir;
	}

	@Override
	public String toString() {
		return "DonneesLocales [id=" + id + ", date=" + date + ", commune=" + commune + ", conditionMeteo="
				+ conditionMeteo + ", qualiteAir=" + qualiteAir + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commune == null) ? 0 : commune.hashCode());
		result = prime * result + ((conditionMeteo == null) ? 0 : conditionMeteo.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((qualiteAir == null) ? 0 : qualiteAir.hashCode());
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
		if (qualiteAir == null) {
			if (other.qualiteAir != null)
				return false;
		} else if (!qualiteAir.equals(other.qualiteAir))
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

	/**
	 * @return the qualiteAir
	 */
	public QualiteAir getQualiteAir() {
		return qualiteAir;
	}

	/**
	 * @param qualiteAir the qualiteAir to set
	 */
	public void setQualiteAir(QualiteAir qualiteAir) {
		this.qualiteAir = qualiteAir;
	}

}
