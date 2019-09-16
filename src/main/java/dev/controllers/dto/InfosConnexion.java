package dev.controllers.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author Cécile Objet DTO qui permet de gérer les identifiants de connexion
 *         d'un utilisateur
 */
public class InfosConnexion {

	/** email : String */
	@NotBlank
	private String email;

	/** motDePasse : String */
	@NotBlank
	private String motDePasse;

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * @param motDePasse
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
}
