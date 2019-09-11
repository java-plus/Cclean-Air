package dev.repositories;

import java.util.Optional;

import dev.entities.Administrateur;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         spécifiques aux profils administrateurs
 *
 */
public interface IAdministrateurRepository extends IUtilisateurRepository<Administrateur> {

	Optional<Administrateur> findByEmailIgnoreCase(String email);
}
