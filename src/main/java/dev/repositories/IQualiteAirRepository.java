package dev.repositories;

import dev.entities.QualiteAir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Classe repository faisant le lien avec la base de données et notamment la table QualiteAir.
 */
public interface IQualiteAirRepository extends JpaRepository<QualiteAir, Integer> {

/*    @Query("select new dev.entities.QualiteAir q FROM QualiteAir order by q.date limit 1")*/
    Optional<QualiteAir> findFirstByOrderByIdDesc();

    List<QualiteAir> findByDate(ZonedDateTime date);


}
