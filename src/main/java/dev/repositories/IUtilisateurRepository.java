package dev.repositories;

import dev.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         communes aux profils membre et utilisateur
 *
 */
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

}
