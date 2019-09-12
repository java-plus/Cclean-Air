package dev.repositories;

import dev.entities.ConditionMeteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Cécile
 * Classe Repository faisant le lien avec la table ConditionMeteo
 */
@Repository
public interface IConditionMeteoRepository extends JpaRepository<ConditionMeteo, Integer> {


}
