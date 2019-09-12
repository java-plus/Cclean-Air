package dev.repositories;

import dev.entities.Commune;
import dev.entities.DonneesLocales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.ZonedDateTime;
import java.util.Optional;

@Repository
public interface IDonneesLocalesRepository extends JpaRepository<DonneesLocales, Integer> {

    Optional<DonneesLocales> findByCommuneAndDate(Commune commune, ZonedDateTime date);
}
