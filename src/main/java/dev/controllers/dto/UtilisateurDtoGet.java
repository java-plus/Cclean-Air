package dev.controllers.dto;

import java.util.List;

import dev.entities.Statut;
import dev.entities.Utilisateur;

public class UtilisateurDtoGet {

	private String nom;
	private String prenom;
	private String email;
	private List<Statut> statut;
	private String nomCommune;
	private String codePostal;
	private Boolean statutNotification;

	public UtilisateurDtoGet() {
	}

	public UtilisateurDtoGet(Utilisateur utilisateur) {
		this.nom = utilisateur.getNom();
		this.prenom = utilisateur.getPrenom();
		this.email = utilisateur.getEmail();
		this.statut = utilisateur.getStatut();
		this.nomCommune = utilisateur.getCommune().getNom();
		this.statutNotification = utilisateur.getStatutNotification();
	}

	public UtilisateurDtoGet(String nom, String prenom, String email, String statut, String nomCommune,
			String codePostal, Boolean statutNotification) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.nomCommune = nomCommune;
		this.codePostal = codePostal;
		this.statutNotification = statutNotification;
	}

	/**
	 * @return the statut
	 */
	public List<Statut> getStatut() {
		return statut;
	}

	/**
	 * @param statut the statut to set
	 */
	public void setStatut(List<Statut> statut) {
		this.statut = statut;
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

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Boolean getStatutNotification() {
		return statutNotification;
	}

	public void setStatutNotification(Boolean statutNotification) {
		this.statutNotification = statutNotification;
	}
}
