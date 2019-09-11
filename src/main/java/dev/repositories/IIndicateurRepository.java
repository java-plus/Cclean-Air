package dev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.entities.Indicateur;

/**
 * @author Guillaume
 *
 */
public interface IIndicateurRepository extends JpaRepository<Indicateur, Integer> {

	public List<Indicateur> findByUtilisateurEmail(String email);

}
