package dev.repositories;

import dev.controllers.dto.visualiserDonnees.QualiteAirDtoVisualisation;
import dev.entities.QualiteAir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface IQualiteAirRepository extends JpaRepository<QualiteAir, Integer> {

    @Query("select  q from QualiteAir q where q.date = :date")
    Optional<QualiteAir> findByDate(@Param("date") ZonedDateTime date);

}

