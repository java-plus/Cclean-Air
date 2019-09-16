package dev.controllers.dto.visualiserDonnees;

public class PolluantDtoVisualisation {

	private String nom;

	private Double Valeur;

	private String unite;

	public PolluantDtoVisualisation() {
	}

	public PolluantDtoVisualisation(String nom, Double valeur, String unite) {
		this.nom = nom;
		Valeur = valeur;
		this.unite = unite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getValeur() {
		return Valeur;
	}

	public void setValeur(Double valeur) {
		Valeur = valeur;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Valeur == null) ? 0 : Valeur.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((unite == null) ? 0 : unite.hashCode());
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
		PolluantDtoVisualisation other = (PolluantDtoVisualisation) obj;
		if (Valeur == null) {
			if (other.Valeur != null)
				return false;
		} else if (!Valeur.equals(other.Valeur))
			return false;
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
		return true;
	}

}
