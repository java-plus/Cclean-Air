
package dev.repositories;

import dev.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

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
}

