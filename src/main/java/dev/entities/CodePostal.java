package dev.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe représentant un code postal de commune.
 */
@Entity
@Table(name = "CODE_POSTAL")
public class CodePostal implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cp_id")
	private Integer id;
	@Column(name = "cp_valeur")
	private String valeur;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cp_commune_id")
	private Commune commune;

	public CodePostal() {
	}

	public CodePostal(String valeur, Commune commune) {
		this.valeur = valeur;
		this.commune = commune;
	}

	public CodePostal(Integer id, String valeur, Commune commune) {
		this.id = id;
		this.valeur = valeur;
		this.commune = commune;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public Commune getCommune() {
		return commune;
	}

	public void setCommune(Commune commune) {
		this.commune = commune;
	}
}
