package dev.repositories;

import dev.entities.Commune;
import dev.entities.DonneesLocales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * @author Cécile
 * Classe Repository faisant le lien avec la table DonneesLocales
 */
@Repository
public interface IDonneesLocalesRepository extends JpaRepository<DonneesLocales, Integer> {

    @Query("select MAX(d.date) from DonneesLocales d where d.commune= :commune")
    ZonedDateTime findByCommune(@Param("commune") Optional<Commune> commune);

    DonneesLocales findByCommuneAndDate(Optional<Commune> commune, ZonedDateTime date);

    Optional<DonneesLocales> findTopByOrderByDateDesc();

    @Transactional
    @Modifying
    @Query("delete from DonneesLocales d where d.date <= ?1")
    void deleteAllExpiredSince(ZonedDateTime zonedDateTime);

}
