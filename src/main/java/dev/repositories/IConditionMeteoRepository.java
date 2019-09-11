package dev.repositories;

import dev.entities.ConditionMeteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe repository faisant le lien avec la base de données et notamment la table ConditionMeteo.
 */
@Repository
public interface IConditionMeteoRepository extends JpaRepository<ConditionMeteo, Integer> {

}
