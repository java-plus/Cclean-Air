package dev.repositories;

import dev.entities.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe repository faisant le lien avec la base de données et notamment la table commune.
 */
@Repository
public interface ICommuneRepository extends JpaRepository<Commune, Integer> {

    Optional<Commune> findByNomIgnoreCase(String nomCommune);
}
