/**
 * 
 */
package dev.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Guillaume Classe mère abstraite pour tous les profils utilisateurs.
 *         Les membres et administrateurs héritent de cette classe et sont
 *         différenciés en base par la colonne "STATUT".
 *
 */
@Entity
@Table(name = "UTILISATEUR")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "uti_statut")
public abstract class Utilisateur implements Serializable {

	private static final long serialVersionUID = 4564093892835148658L;

	/**
	 * Identifiant unique utilisateur, clé primaire en base de données.
	 */
	@Id
	@Column(name = "uti_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	/**
	 * Nom de famille de l'utilisateur
	 */
	@Column(name = "uti_nom")
	protected String nom;
	/**
	 * Prénom de l'utilisateur
	 */
	@Column(name = "uti_prenom")
	protected String prenom;
	/**
	 * Email de l'utilisateur. Utilisé pour l'inscription et la connexion. Doit être
	 * unique en base.
	 */
	@Column(name = "uti_email")
	protected String email;
	/**
	 * Mot de pase choisi par l'utilisateur lors de l'inscription. Utilisé pour se
	 * connecter. Le mot de passe doit comporter au minimum 8 caractères dont un
	 * chiffre, une majuscule, une minuscule et un caractère spécial au minimum.
	 */
	@Column(name = "uti_mot_de_passe")
	protected String motDePasse;
	/**
	 * Énumération utilisée pour classifier les profils d'utilisateurs en
	 * administrateur ou en utilisateur.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "uti_statut")
	protected Statut statut;
	/**
	 * Indique si l'utilisateur souhaite, ou non, recevoir des notifications sur
	 * certaines alertes
	 */
	@Column(name = "uti_statut_notification")
	protected Boolean statutNotification;
	/**
	 * Attibut servant à comptabiliser les tentatives de connexion infructueuses sur
	 * un compte donnée. Au bout d'un certains nombres d'échecs, des restrictions
	 * seront appliquées
	 */
	@Column(name = "uti_tentative_connexion")
	protected Integer compteurTentativesConnexion;

	public Utilisateur() {

	}

	public Utilisateur(String nom, String prenom, String email, String motDePasse, Statut statut,
			Boolean statutNotification, Integer compteurTentativesConnexion) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.statut = statut;
		this.statutNotification = statutNotification;
		this.compteurTentativesConnexion = compteurTentativesConnexion;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse="
				+ motDePasse + ", statut=" + statut + ", statutNotification=" + statutNotification
				+ ", compteurTentativesConnexion=" + compteurTentativesConnexion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compteurTentativesConnexion == null) ? 0 : compteurTentativesConnexion.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((statut == null) ? 0 : statut.hashCode());
		result = prime * result + ((statutNotification == null) ? 0 : statutNotification.hashCode());
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
		Utilisateur other = (Utilisateur) obj;
		if (compteurTentativesConnexion == null) {
			if (other.compteurTentativesConnexion != null)
				return false;
		} else if (!compteurTentativesConnexion.equals(other.compteurTentativesConnexion))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (motDePasse == null) {
			if (other.motDePasse != null)
				return false;
		} else if (!motDePasse.equals(other.motDePasse))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (statut != other.statut)
			return false;
		if (statutNotification == null) {
			if (other.statutNotification != null)
				return false;
		} else if (!statutNotification.equals(other.statutNotification))
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
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * @return the statut
	 */
	public Statut getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	/**
	 * @return the statutNotification
	 */
	public Boolean getStatutNotification() {
		return statutNotification;
	}

	/**
	 * @param statutNotification the statutNotification to set
	 */
	public void setStatutNotification(Boolean statutNotification) {
		this.statutNotification = statutNotification;
	}

	/**
	 * @return the compteurTentativesConnexion
	 */
	public Integer getCompteurTentativesConnexion() {
		return compteurTentativesConnexion;
	}

	/**
	 * @param compteurTentativesConnexion the compteurTentativesConnexion to set
	 */
	public void setCompteurTentativesConnexion(Integer compteurTentativesConnexion) {
		this.compteurTentativesConnexion = compteurTentativesConnexion;
	}

}
