/**
 * 
 */
package dev.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Guillaume Classe regroupant les profils administrateurs avec des
 *         privilèges particuliers.
 *
 */
@Entity
@Table(name = "ADMINISTRATEUR")
@DiscriminatorValue(value = "administrateur")
public class Administrateur extends Utilisateur implements Serializable {

	private static final long serialVersionUID = 137954808882912440L;

	public Administrateur() {
		this.statut = Statut.ADMINISTRATEUR;
	}

	@Override
	public String toString() {
		return "Administrateur []";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
