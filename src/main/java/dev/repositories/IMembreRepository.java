package dev.repositories;

import dev.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         spécifiques aux profils membre
 *
 */
public interface IMembreRepository extends JpaRepository<Membre, Integer> {

    Optional<Membre> findByEmailIgnoreCase(String email);
}
