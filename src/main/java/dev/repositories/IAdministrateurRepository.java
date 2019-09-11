package dev.repositories;

import dev.entities.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         spécifiques aux profils administrateurs
 *
 */
public interface IAdministrateurRepository extends JpaRepository<Administrateur, Integer> {

    Optional<Administrateur> findByEmailIgnoreCase(String email);
}
