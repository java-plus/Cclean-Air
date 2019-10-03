package dev.controllers.dto.recherche.resultat;

/**
 * @author Guillaume
 *
 */
public class PolluantDto {

	private String nom;
	private String unite;
	private Double valeur;

	public PolluantDto(String nom, String unite, Double valeur) {
		super();
		this.nom = nom;
		this.unite = unite;
		this.valeur = valeur;
	}

	public PolluantDto() {
		super();
	}

	@Override
	public String toString() {
		return "PolluantDto [nom=" + nom + ", unite=" + unite + ", valeur=" + valeur + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((unite == null) ? 0 : unite.hashCode());
		result = prime * result + ((valeur == null) ? 0 : valeur.hashCode());
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
		PolluantDto other = (PolluantDto) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (unite == null) {
			if (other.unite != null)
				return false;
		} else if (!unite.equals(other.unite))
			return false;
		if (valeur == null) {
			if (other.valeur != null)
				return false;
		} else if (!valeur.equals(other.valeur))
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
	 * @return the unite
	 */
	public String getUnite() {
		return unite;
	}

	/**
	 * @param unite the unite to set
	 */
	public void setUnite(String unite) {
		this.unite = unite;
	}

	/**
	 * @return the valeur
	 */
	public Double getValeur() {
		return valeur;
	}

	/**
	 * @param valeur the valeur to set
	 */
	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

}
