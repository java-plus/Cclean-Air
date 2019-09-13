
package dev.repositories;


import dev.controllers.dto.UtilisateurDtoAdmin;
import dev.entities.Utilisateur;
import dev.services.UtilisateurService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         communes aux profils membre et utilisateur
 *
 */
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    /**
     * méthode qui recherche un Utilisateur à partir de son email
     * @param email
     * @return
     */
    Optional<Utilisateur> findByEmailIgnoreCase(String email);


    @Query("select new dev.controllers.dto.UtilisateurDtoAdmin(u.nom, u.prenom, u.email) from Utilisateur u")
    List<UtilisateurDtoAdmin> findAllwithNomPrenomEmail();

}
