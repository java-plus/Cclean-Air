package dev.controllers.dto;

import java.util.List;

/**
 * @author Cécile Objet Dto qui sert pour modifier le profil
 */
public class ProfilModificationPost {
	/**
	 * nom de l'utilisateur
	 */

	private String nom;
	/**
	 * prénom de l'utilisateur
	 */

	private String prenom;
	/**
	 * mail de l'utilisateur
	 */

	private String email;
	/**
	 * commune de l'utilisateur
	 */

	private String commune;
	/**
	 * Acceptation ou non des alertes
	 */

	private Boolean statutNotification;

	/**
	 * Liste des alertes par indicateru
	 */
	private List<CommuneIndicateurDto> listeIndicateurs;

	/**
	 * mot de passe actuel de l'utilisateur
	 */

	private String motDePasseActuel;
	/**
	 * nouveau mot de passe de l'utilisateur
	 */
	// @Pattern(regexp =
	// "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message =
	// "Le mot de passe ne respecte pas les règles de sécurité")

	private String motDePasseNouveau;
	/**
	 * controle du nouveau mot de passe de l'utilisateur
	 */

	// @Pattern(regexp =
	// "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message =
	// "Le mot de passe ne respecte pas les règles de sécurité")

	private String getMotDePasseNouveauValidation;

	public ProfilModificationPost(String nom, String prenom, String email, String commune, Boolean statutNotification,
			List<CommuneIndicateurDto> listeIndicateurs, String motDePasseActuel, String motDePasseNouveau,
			String getMotDePasseNouveauValidation) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.commune = commune;
		this.statutNotification = statutNotification;
		this.listeIndicateurs = listeIndicateurs;
		this.motDePasseActuel = motDePasseActuel;
		this.motDePasseNouveau = motDePasseNouveau;
		this.getMotDePasseNouveauValidation = getMotDePasseNouveauValidation;
	}

	public ProfilModificationPost() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public Boolean getstatutNotification() {
		return statutNotification;
	}

	public void setstatutNotification(Boolean statutNotification) {
		this.statutNotification = statutNotification;
	}

	public String getMotDePasseActuel() {
		return motDePasseActuel;
	}

	public void setMotDePasseActuel(String motDePasseActuel) {
		this.motDePasseActuel = motDePasseActuel;
	}

	public String getMotDePasseNouveau() {
		return motDePasseNouveau;
	}

	public void setMotDePasseNouveau(String motDePasseNouveau) {
		this.motDePasseNouveau = motDePasseNouveau;
	}

	public String getGetMotDePasseNouveauValidation() {
		return getMotDePasseNouveauValidation;
	}

	public void setGetMotDePasseNouveauValidation(String getMotDePasseNouveauValidation) {
		this.getMotDePasseNouveauValidation = getMotDePasseNouveauValidation;
	}

	public List<CommuneIndicateurDto> getListeIndicateurs() {
		return listeIndicateurs;
	}

	public void setListeIndicateurs(List<CommuneIndicateurDto> listeIndicateurs) {
		this.listeIndicateurs = listeIndicateurs;
	}
}
