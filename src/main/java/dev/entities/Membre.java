/**
 * 
 */
package dev.entities;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Guillaume Classe regroupant les profils standards inscrits à
 *         l'application.
 *
 */
@Entity
@Table(name = "MEMBRE")
@DiscriminatorValue(value = "membre")
public class Membre extends Utilisateur implements Serializable {

	private static final long serialVersionUID = -729380413330096128L;

	/**
	 * Indique la date de dernière connexion du membre. Au bout d'une certaine
	 * période d'innactivité, des actions seront entreprises.
	 */
	@Column(name = "uti_date_derniere_connexion")
	private ZonedDateTime dateDerniereConnexion;

	public Membre() {
		this.statut = Statut.MEMBRE;
	}

	public Membre(ZonedDateTime dateDerniereConnexion) {
		super();
		this.statut = Statut.MEMBRE;
		this.dateDerniereConnexion = dateDerniereConnexion;
	}

	@Override
	public String toString() {
		return "Membre [dateDerniereConnexion=" + dateDerniereConnexion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateDerniereConnexion == null) ? 0 : dateDerniereConnexion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Membre other = (Membre) obj;
		if (dateDerniereConnexion == null) {
			if (other.dateDerniereConnexion != null)
				return false;
		} else if (!dateDerniereConnexion.equals(other.dateDerniereConnexion))
			return false;
		return true;
	}

	/**
	 * @return the dateDerniereConnexion
	 */
	public ZonedDateTime getDateDerniereConnexion() {
		return dateDerniereConnexion;
	}

	/**
	 * @param dateDerniereConnexion the dateDerniereConnexion to set
	 */
	public void setDateDerniereConnexion(ZonedDateTime dateDerniereConnexion) {
		this.dateDerniereConnexion = dateDerniereConnexion;
	}

}
