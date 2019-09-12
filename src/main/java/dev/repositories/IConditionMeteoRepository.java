package dev.repositories;

import dev.controllers.dto.visualiserDonnees.ConditionMeteoDtoVisualisation;
import dev.entities.ConditionMeteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface IConditionMeteoRepository extends JpaRepository<ConditionMeteo, Integer> {

    @Query("select new dev.controllers.dto.visualiserDonnees.ConditionMeteoDtoVisualisation (c.ensoleillement, c.temperature, c.humidite) from ConditionMeteo c")
    Optional<ConditionMeteoDtoVisualisation> findByDate(ZonedDateTime date);
}
