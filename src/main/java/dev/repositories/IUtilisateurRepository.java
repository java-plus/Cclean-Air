package dev.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Utilisateur;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         communes aux profils membre et utilisateur
 *
 */

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	Optional<Utilisateur> findByEmailIgnoreCase(String email);
}
