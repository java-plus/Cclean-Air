package dev.controllers.dto.visualiserDonnees;

/**
 * @author Cécile
 * Objet DTO qui va retourner le nom de  la commune et le nombre d'habitants
 * Utiliser par DonneesLocalesDTO pour afficher les données
 */
public class CommuneDtoVisualisation {

    /**
     * Nom de la commune
     */
    private  String nom;

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
}
