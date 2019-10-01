package dev.controllers.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author Cécile Objet DTO qui permet de gérer les identifiants de connexion
 *         d'un utilisateur
 */
public class InfosConnexion {

	@NotBlank
	private String email;

	@NotBlank
	private String motDePasse;

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
}
