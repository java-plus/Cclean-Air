package dev.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

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
	@NotBlank
	protected String nom;
	/**
	 * Prénom de l'utilisateur
	 */
	@Column(name = "uti_prenom")
	@NotBlank
	protected String prenom;
	/**
	 * Email de l'utilisateur. Utilisé pour l'inscription et la connexion. Doit être
	 * unique en base.
	 */
	@Column(name = "uti_email", unique = true)
	@NotBlank
	@Email
	protected String email;
	/**
	 * Mot de pase choisi par l'utilisateur lors de l'inscription. Utilisé pour se
	 * connecter. Le mot de passe doit comporter au minimum 8 caractères dont un
	 * chiffre, une majuscule, une minuscule et un caractère spécial au minimum.
	 */
	@Column(name = "uti_mot_de_passe")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
	@NotBlank
	protected String motDePasse;
	/**
	 * Énumération utilisée pour classifier les profils d'utilisateurs en
	 * administrateur ou en utilisateur.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@Column(name = "uti_statut")
	protected List<Statut> statut;
	/**
	 * Indique si l'utilisateur souhaite, ou non, recevoir des notifications sur
	 * certaines alertes
	 */
	@Column(name = "uti_statut_notification")
	@NotNull
	protected Boolean statutNotification;
	/**
	 * Attibut servant à comptabiliser les tentatives de connexion infructueuses sur
	 * un compte donnée. Au bout d'un certains nombres d'échecs, des restrictions
	 * seront appliquées
	 */
	@Column(name = "uti_tentative_connexion")
	protected Integer compteurTentativesConnexion;

	@OneToMany(mappedBy = "utilisateur")
	@Column(name = "liste_indicateurs")
	protected List<Indicateur> listeIndicateurs;

	@ManyToOne
	protected Commune commune;

	public Utilisateur() {
	}

	public Utilisateur(@NotBlank String nom, @NotBlank String prenom, @NotBlank @Email String email, @Pattern(regexp =
			"^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") @NotBlank String motDePasse,
					   List<Statut> statut, @NotNull Boolean statutNotification, Integer compteurTentativesConnexion,
					   List<Indicateur> listeIndicateurs, Commune commune) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.statut = statut;
		this.statutNotification = statutNotification;
		this.compteurTentativesConnexion = compteurTentativesConnexion;
		this.listeIndicateurs = listeIndicateurs;
		this.commune = commune;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse="
				+ motDePasse + ", statut=" + statut + ", statutNotification=" + statutNotification
				+ ", compteurTentativesConnexion=" + compteurTentativesConnexion + ", listeIndicateurs="
				+ listeIndicateurs + "]";
	}

	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
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

	public List<Statut> getStatut() {
		return statut;
	}

	public void setStatut(List<Statut> statut) {
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

	/**
	 * @return the listeIndicateurs
	 */
	public List<Indicateur> getListeIndicateurs() {
		return listeIndicateurs;
	}

	/**
	 * @param listeIndicateurs the listeIndicateurs to set
	 */
	public void setListeIndicateurs(List<Indicateur> listeIndicateurs) {
		this.listeIndicateurs = listeIndicateurs;
	}

}
