package dev.controllers.dto;

/**
 * @author Guillaume Utilisé pour représenter le niveau d'alerte d'une commune
 *         qui va être envoyée à l'interface. Peut être null.
 */
public class NiveauAlerteDto {

	private String nomPolluant;
	private Double valeur;

	public NiveauAlerteDto(String nomPolluant, Double valeur) {
		super();
		this.nomPolluant = nomPolluant;
		this.valeur = valeur;
	}

	public NiveauAlerteDto() {
		super();
	}

	@Override
	public String toString() {
		return "NiveauAlerteDto [nomPolluant=" + nomPolluant + ", valeur=" + valeur + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomPolluant == null) ? 0 : nomPolluant.hashCode());
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
		NiveauAlerteDto other = (NiveauAlerteDto) obj;
		if (nomPolluant == null) {
			if (other.nomPolluant != null)
				return false;
		} else if (!nomPolluant.equals(other.nomPolluant))
			return false;
		if (valeur == null) {
			if (other.valeur != null)
				return false;
		} else if (!valeur.equals(other.valeur))
			return false;
		return true;
	}

	/**
	 * @return the nomPolluant
	 */
	public String getNomPolluant() {
		return nomPolluant;
	}

	/**
	 * @param nomPolluant the nomPolluant to set
	 */
	public void setNomPolluant(String nomPolluant) {
		this.nomPolluant = nomPolluant;
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
