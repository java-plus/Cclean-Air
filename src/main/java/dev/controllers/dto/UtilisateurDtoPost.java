package dev.controllers.dto;

import dev.entities.Statut;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UtilisateurDtoPost {

	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	@Email
	private String email;
	@NotBlank
	private String motDePasse;
	private List<Statut> statuts;
	@NotBlank
	private String nomCommune;
	@NotBlank
	private String codePostal;
	@NotNull
	private Boolean statutNotification;

	public UtilisateurDtoPost() {
	}

	public UtilisateurDtoPost(@NotBlank String nom, @NotBlank String prenom, @Email String email,
			@NotBlank String motDePasse, List<Statut> statuts, @NotBlank String nomCommune, @NotBlank String codePostal,
			@NotNull Boolean statutNotification) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.statuts = statuts;
		this.nomCommune = nomCommune;
		this.codePostal = codePostal;
		this.statutNotification = statutNotification;
	}

	public List<Statut> getStatuts() {
		return statuts;
	}

	public void setStatuts(List<Statut> statuts) {
		this.statuts = statuts;
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

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
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
