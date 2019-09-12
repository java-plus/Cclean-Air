package dev.entities;

import javax.persistence.*;
import java.io.Serializable;

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
    @ManyToOne
    @JoinColumn(name = "cp_commune_id")
    private Commune commune;

    public CodePostal() {
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
