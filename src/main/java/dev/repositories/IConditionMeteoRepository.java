package dev.repositories;

import dev.entities.ConditionMeteo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConditionMeteoRepository extends JpaRepository<ConditionMeteo, Integer> {

}
