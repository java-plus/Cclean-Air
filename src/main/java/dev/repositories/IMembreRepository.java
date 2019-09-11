package dev.repositories;

import java.util.Optional;

import dev.entities.Membre;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         spécifiques aux profils membre
 *
 */
public interface IMembreRepository extends IUtilisateurRepository<Membre> {

	Optional<Membre> findByEmailIgnoreCase(String email);
}
