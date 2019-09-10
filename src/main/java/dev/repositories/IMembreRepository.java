/**
 * 
 */
package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Membre;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         spécifiques aux profils membre
 *
 */
public interface IMembreRepository extends JpaRepository<Membre, Integer> {

}
