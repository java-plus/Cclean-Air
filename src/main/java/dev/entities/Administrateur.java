/**
 * 
 */
package dev.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collections;

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
		this.statut = Collections.singletonList(Statut.ADMINISTRATEUR);
	}

	public Administrateur(String nom, String prenom, String email, String motDePasse, Boolean statutNotification,
				  Integer compteurTentativesConnexion) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.statut = Collections.singletonList(Statut.ADMINISTRATEUR);
		this.statutNotification = statutNotification;
		this.compteurTentativesConnexion = compteurTentativesConnexion;
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
