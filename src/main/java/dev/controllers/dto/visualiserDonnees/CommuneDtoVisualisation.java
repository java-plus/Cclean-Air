package dev.controllers.dto.visualiserDonnees;

/**
 * @author Cécile Objet DTO qui va retourner le nom de la commune et le nombre
 *         d'habitants Utiliser par DonneesLocalesDTO pour afficher les données
 */
public class CommuneDtoVisualisation {

	/**
	 * Nom de la commune
	 */
	private String nom;

	/**
	 * Nombre d'habitants dans la commune
	 */
	private Long nbHabitants;

	public CommuneDtoVisualisation() {
	}

	public CommuneDtoVisualisation(String nom, Long nbHabitants) {
		this.nom = nom;
		this.nbHabitants = nbHabitants;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getNbHabitants() {
		return nbHabitants;
	}

	public void setNbHabitants(Long nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nbHabitants == null) ? 0 : nbHabitants.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		CommuneDtoVisualisation other = (CommuneDtoVisualisation) obj;
		if (nbHabitants == null) {
			if (other.nbHabitants != null)
				return false;
		} else if (!nbHabitants.equals(other.nbHabitants))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
