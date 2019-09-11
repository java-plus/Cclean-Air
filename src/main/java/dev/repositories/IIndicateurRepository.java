package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Indicateur;

/**
 * @author Guillaume
 *
 */
public interface IIndicateurRepository extends JpaRepository<Indicateur, Integer> {

}
