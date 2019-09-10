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
 * @author Guillaume Classe utilisée pour la gestion des indicateurs de chaque
 *         utilisateur
 *
 */
@Entity
@Table(name = "INDICATEUR")
public class Indicateur implements Serializable {

	private static final long serialVersionUID = 2493151450532953083L;

	/**
	 * Identifiant unique d'un indicateur, clé primaire de la table associée
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ind_id")
	private Integer id;

	/**
	 * Utilisateur à qui appartient l'indicateur en question
	 */
	@JoinColumn(name = "ind_utilisateur")
	private Utilisateur utilisateur;

	/**
	 * Commune à laquelle fait référence l'indicateur
	 */
	@ManyToOne
	@JoinColumn(name = "ind_commune")
	private Commune commune;

	public Indicateur(Integer id, Utilisateur utilisateur) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
	}

	public Indicateur() {
		super();
	}

	@Override
	public String toString() {
		return "Indicateur [id=" + id + ", utilisateur=" + utilisateur + ", commune=" + commune + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commune == null) ? 0 : commune.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
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
		Indicateur other = (Indicateur) obj;
		if (commune == null) {
			if (other.commune != null)
				return false;
		} else if (!commune.equals(other.commune))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
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
	 * @return the utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * @return the commune
	 */
	public Commune getCommune() {
		return commune;
	}

	/**
	 * @param commune the commune to set
	 */
	public void setCommune(Commune commune) {
		this.commune = commune;
	}

}
