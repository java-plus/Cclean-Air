package dev.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe représentant une commune géographique.
 */
@Entity
@Table(name = "COMMUNE")
public class Commune implements Serializable {

	private static final long serialVersionUID = 3795370245957064865L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "com_id")
	private Integer id;
	@Column(name = "com_nom")
	private String nom;
	@Column(name = "com_nb_habitants")
	private Long nbHabitants;
	@Column(name = "com_code_insee")
	private String codeInsee;
	@Column(name = "com_latitude")
	private Double latitude;
	@Column(name = "com_longitude")
	private Double longitude;

	/**
	 * Indicateurs qui concernent la commune en question
	 */
	@OneToMany(mappedBy = "commune", cascade = CascadeType.ALL)
	@Column(name = "com_liste_indicateurs")
	private List<Indicateur> listeIndicateurs;

	/**
	 * Donnes locales de la commune
	 */
	@OneToMany(mappedBy = "commune")
	@Column(name = "com_donnees_locales")
	private List<DonneesLocales> donnesLocales;

	/**
	 * Liste des notifications et alertes concernant une commune spécifiques
	 */
	@OneToMany(mappedBy = "commune", cascade = CascadeType.ALL)
	@Column(name = "com_liste_notifications")
	private List<Notification> listeNotifications;

	/**
	 * Constructeur
	 */
	public Commune() {
	}

	/**
	 * Constructeur
	 * 
	 * @param nom         : [String] nom de la commune
	 * @param nbHabitants : [Long] nombre d'habitants dans la commune
	 * @param codeInsee   : [String] code insee de la commune
	 * @param latitude    : [Double] latitude géographique de la commune
	 * @param longitude   : [Double] longitude géographique de la commune
	 */
	public Commune(String nom, Long nbHabitants, String codeInsee, Double latitude, Double longitude) {
		this.nom = nom;
		this.nbHabitants = nbHabitants;
		this.codeInsee = codeInsee;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Commune(String nom, Long nbHabitants, String codeInsee, Double latitude, Double longitude,
			List<Indicateur> listeIndicateurs, List<DonneesLocales> donnesLocales,
			List<Notification> listeNotifications) {
		super();
		this.nom = nom;
		this.nbHabitants = nbHabitants;
		this.codeInsee = codeInsee;
		this.latitude = latitude;
		this.longitude = longitude;
		this.listeIndicateurs = listeIndicateurs;
		this.donnesLocales = donnesLocales;
		this.listeNotifications = listeNotifications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeInsee == null) ? 0 : codeInsee.hashCode());
		result = prime * result + ((donnesLocales == null) ? 0 : donnesLocales.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((listeIndicateurs == null) ? 0 : listeIndicateurs.hashCode());
		result = prime * result + ((listeNotifications == null) ? 0 : listeNotifications.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		Commune other = (Commune) obj;
		if (codeInsee == null) {
			if (other.codeInsee != null)
				return false;
		} else if (!codeInsee.equals(other.codeInsee))
			return false;
		if (donnesLocales == null) {
			if (other.donnesLocales != null)
				return false;
		} else if (!donnesLocales.equals(other.donnesLocales))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (listeIndicateurs == null) {
			if (other.listeIndicateurs != null)
				return false;
		} else if (!listeIndicateurs.equals(other.listeIndicateurs))
			return false;
		if (listeNotifications == null) {
			if (other.listeNotifications != null)
				return false;
		} else if (!listeNotifications.equals(other.listeNotifications))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCodeInsee() {
		return codeInsee;
	}

	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the listeIndicateurs
	 */
	public List<Indicateur> getListeIndicateurs() {
		return listeIndicateurs;
	}

	/**
	 * @param listeIndicateurs the listeIndicateurs to set
	 */
	public void setListeIndicateurs(List<Indicateur> listeIndicateurs) {
		this.listeIndicateurs = listeIndicateurs;
	}

	/**
	 * @return the donnesLocales
	 */
	public List<DonneesLocales> getDonnesLocales() {
		return donnesLocales;
	}

	/**
	 * @param donnesLocales the donnesLocales to set
	 */
	public void setDonnesLocales(List<DonneesLocales> donnesLocales) {
		this.donnesLocales = donnesLocales;
	}

	/**
	 * @return the listeNotifications
	 */
	public List<Notification> getListeNotifications() {
		return listeNotifications;
	}

	/**
	 * @param listeNotifications the listeNotifications to set
	 */
	public void setListeNotifications(List<Notification> listeNotifications) {
		this.listeNotifications = listeNotifications;
	}

	@Override
	public String toString() {
		return "Commune [id=" + id + ", nom=" + nom + ", nbHabitants=" + nbHabitants + ", codeInsee=" + codeInsee
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", listeIndicateurs=" + listeIndicateurs
				+ ", donnesLocales=" + donnesLocales + ", listeNotifications=" + listeNotifications + "]";
	}
}
