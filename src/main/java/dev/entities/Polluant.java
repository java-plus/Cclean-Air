package dev.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Guillaume Classe regroupant les informations sur les polluants
 *
 */
@Entity
@Table(name = "POLLUANT")
public class Polluant implements Serializable {

	private static final long serialVersionUID = -8475977458010349912L;

	/**
	 * Identifiant unique du polluant, clé primaire de la table associée
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pol_id")
	private Integer id;

	/**
	 * Unité de mesure avec laquelle est mesurée le polluant
	 */
	@Column(name = "pol_unite")
	private String unite;

	/**
	 * Nom du polluant
	 */
	@Column(name = "pol_nom")
	private String nom;

	/**
	 * Mesure chiffrée du polluant
	 */
	@Column(name = "pol_valeur")
	private Double valeur;

	/**
	 * Donnée à laquelle sera associée le polluant
	 */
	@ManyToOne
	@JoinColumn(name = "pol_qualite_air")
	private QualiteAir qualiteAir;

	public Polluant(Integer id, String unite, String nom, Double valeur, QualiteAir qualiteAir) {
		super();
		this.id = id;
		this.unite = unite;
		this.nom = nom;
		this.valeur = valeur;
		this.qualiteAir = qualiteAir;
	}

	public Polluant() {
		super();
	}

	@Override
	public String toString() {
		return "Polluant [id=" + id + ", unite=" + unite + ", nom=" + nom + ", valeur=" + valeur + ", qualiteAir="
				+ qualiteAir + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((qualiteAir == null) ? 0 : qualiteAir.hashCode());
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
		Polluant other = (Polluant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (qualiteAir == null) {
			if (other.qualiteAir != null)
				return false;
		} else if (!qualiteAir.equals(other.qualiteAir))
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
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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

	/**
	 * @return the qualiteAir
	 */
	public QualiteAir getQualiteAir() {
		return qualiteAir;
	}

	/**
	 * @param qualiteAir the qualiteAir to set
	 */
	public void setQualiteAir(QualiteAir qualiteAir) {
		this.qualiteAir = qualiteAir;
	}

}
