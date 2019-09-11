<<<<<<< HEAD

package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Utilisateur;

import java.util.Locale;
import java.util.Optional;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         communes aux profils membre et utilisateur
 *
 */
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    /**
     * Méthode qui retrouve un utilisateur en fonction de son email.
     * @param email
     * @return
     */
    Optional<Utilisateur> findByEmailIgnoreCase(String email);
}
=======
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
    Optional<Utilisateur> findByEmailIgnoreCase(String email);
}
>>>>>>> master
