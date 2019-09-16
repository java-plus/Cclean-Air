package dev.controllers.dto.visualiserDonnees;

import dev.entities.Polluant;

import java.util.List;

/**
 * @author Cécile
 * Objet dto qui retourne la qualité de l'air
 */
public class QualiteAirDtoVisualisation {


    /**
     * liste de polluants
     */
    private List<Polluant> listePolluants;

    public QualiteAirDtoVisualisation() {
    }

    public QualiteAirDtoVisualisation(List<Polluant> listePolluants) {
        this.listePolluants = listePolluants;
    }

    public List<Polluant> getListePolluants() {
        return listePolluants;
    }

    public void setListePolluants(List<Polluant> listePolluants) {
        this.listePolluants = listePolluants;
    }
}
