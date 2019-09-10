/**
 * 
 */
package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Administrateur;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         spécifiques aux profils administrateurs
 *
 */
public interface IAdministrateurRepository extends JpaRepository<Administrateur, Integer> {

}
