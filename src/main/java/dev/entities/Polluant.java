package dev.entities;

import javax.persistence.*;
import java.io.Serializable;

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
	private String id;

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
	 * Nom de code du polluant
	 */
	@Column(name = "pol_code")
	private String code;

	/**
	 * Mesure chiffrée du polluant
	 */
	@Column(name = "pol_valeur")
	private Double valeur;

	/**
	 * Donnée à laquelle sera associée le polluant
	 */
	@ManyToOne
	@JoinColumn(name = "pol_qualite_air_id")
	private QualiteAir qualiteAir;

	public Polluant(String code, String nom) {
		this.code= code;
		this.nom = nom;
	}

	public Polluant(String unite, String nom, String code, Double valeur) {
		this.unite = unite;
		this.nom = nom;
		this.code = code;
		this.valeur = valeur;
	}

	public Polluant(String unite, String nom, String code, Double valeur, QualiteAir qualiteAir) {
		this.unite = unite;
		this.nom = nom;
		this.code = code;
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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
