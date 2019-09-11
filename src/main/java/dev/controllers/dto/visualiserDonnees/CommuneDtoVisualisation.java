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
    private String nbHabitants;


    public CommuneDtoVisualisation() {
    }

    public CommuneDtoVisualisation(String nom, String nbHabitants) {
        this.nom = nom;
        this.nbHabitants = nbHabitants;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNbHabitants() {
        return nbHabitants;
    }

    public void setNbHabitants(String nbHabitants) {
        this.nbHabitants = nbHabitants;
    }
}
