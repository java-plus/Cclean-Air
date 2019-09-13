package dev.controllers.dto;

import java.util.List;

/**
 * @author Guillaume Classe utilisée pour 'laffichage de la vu du profil
 *         utilisateur
 *
 */
public class ProfilDtoGet {

	private String nom;
	private String prenom;
	private String email;
	private String commune;
	private List<CommuneIndicateurDto> listeIndicateurs;
	private Boolean statutNotification;
	private String motDePasse;

	public ProfilDtoGet(String nom, String prenom, String email, String commune,
			List<CommuneIndicateurDto> listeIndicateurs, Boolean statutNotification, String motDePasse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.commune = commune;
		this.listeIndicateurs = listeIndicateurs;
		this.statutNotification = statutNotification;
		this.motDePasse = motDePasse;
	}

	public ProfilDtoGet() {
		super();
	}

	@Override
	public String toString() {
		return "ProfilDtoGet [nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", commune=" + commune
				+ ", listeIndicateurs=" + listeIndicateurs + ", statutNotification=" + statutNotification
				+ ", motDePasse=" + motDePasse + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commune == null) ? 0 : commune.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((listeIndicateurs == null) ? 0 : listeIndicateurs.hashCode());
		result = prime * result + ((motDePasse == null) ? 0 : motDePasse.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		ProfilDtoGet other = (ProfilDtoGet) obj;
		if (commune == null) {
			if (other.commune != null)
				return false;
		} else if (!commune.equals(other.commune))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (listeIndicateurs == null) {
			if (other.listeIndicateurs != null)
				return false;
		} else if (!listeIndicateurs.equals(other.listeIndicateurs))
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
		if (statutNotification == null) {
			if (other.statutNotification != null)
				return false;
		} else if (!statutNotification.equals(other.statutNotification))
			return false;
		return true;
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
	 * @return the commune
	 */
	public String getCommune() {
		return commune;
	}

	/**
	 * @param commune the commune to set
	 */
	public void setCommune(String commune) {
		this.commune = commune;
	}

	/**
	 * @return the listeIndicateurs
	 */
	public List<CommuneIndicateurDto> getListeIndicateurs() {
		return listeIndicateurs;
	}

	/**
	 * @param listeIndicateurs the listeIndicateurs to set
	 */
	public void setListeIndicateurs(List<CommuneIndicateurDto> listeIndicateurs) {
		this.listeIndicateurs = listeIndicateurs;
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

}
