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
 * @author Guillaume Classe regroupant les données utilisées pour la mesure de
 *         la qualité de l'air
 *
 */
@Entity
@Table(name = "QUALITE_AIR")
public class QualiteAir implements Serializable {

	private static final long serialVersionUID = 5784440834842449303L;

	/**
	 * Identifiant unique de la données, clé primaire de la table associée
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qua_id")
	private Integer id;

	/**
	 * Indicateur temporel de la donnée
	 */
	@Column(name = "qua_date")
	private ZonedDateTime date;

	/**
	 * Liste des donnes dans lesquelles seront stockées les informations de qualité
	 * de l'air
	 */
	@OneToMany(mappedBy = "qualiteAir")
	@Column(name = "qua_liste_donnees")
	private List<DonneesLocales> listeDonnees;

	/**
	 * Liste des polluants associées à la donnée de qualité d'air spécifique
	 */
	@OneToMany(mappedBy = "qualiteAir")
	@Column(name = "qua_liste_polluants")
	private List<Polluant> listePolluants;

	public QualiteAir(ZonedDateTime date, List<DonneesLocales> listeDonnees, List<Polluant> listePolluants) {
		super();
		this.date = date;
		this.listeDonnees = listeDonnees;
		this.listePolluants = listePolluants;
	}

	public QualiteAir() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listeDonnees == null) ? 0 : listeDonnees.hashCode());
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
		QualiteAir other = (QualiteAir) obj;
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
		if (listeDonnees == null) {
			if (other.listeDonnees != null)
				return false;
		} else if (!listeDonnees.equals(other.listeDonnees))
			return false;
		if (listePolluants == null) {
			if (other.listePolluants != null)
				return false;
		} else if (!listePolluants.equals(other.listePolluants))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QualiteAir [id=" + id + ", date=" + date + ", listeDonnees=" + listeDonnees + ", listePolluants="
				+ listePolluants + "]";
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
	 * @return the listeDonnees
	 */
	public List<DonneesLocales> getListeDonnees() {
		return listeDonnees;
	}

	/**
	 * @param listeDonnees the listeDonnees to set
	 */
	public void setListeDonnees(List<DonneesLocales> listeDonnees) {
		this.listeDonnees = listeDonnees;
	}

	/**
	 * @return the listePolluants
	 */
	public List<Polluant> getListePolluants() {
		return listePolluants;
	}

	/**
	 * @param listePolluants the listePolluants to set
	 */
	public void setListePolluants(List<Polluant> listePolluants) {
		this.listePolluants = listePolluants;
	}

}
