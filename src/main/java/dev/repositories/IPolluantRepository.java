package dev.repositories;

import dev.entities.Polluant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe repository faisant le lien avec la base de données et notamment la table polluant.
 */
public interface IPolluantRepository extends JpaRepository<Polluant, Integer> {

}
