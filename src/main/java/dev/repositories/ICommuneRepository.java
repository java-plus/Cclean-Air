package dev.repositories;

import dev.controllers.dto.visualiserDonnees.CommuneDtoVisualisation;
import dev.entities.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Classe repository faisant le lien avec la base de données et notamment la table commune.
 */
@Repository
public interface ICommuneRepository extends JpaRepository<Commune, Integer> {

    Optional<Commune> findByNomIgnoreCase(String nomCommune);


    @Query("select new dev.controller.dto.visualiserDonnees.CommuneDtoVisualisation(c.nom, c.nbHabitants) from Commune c")
    Optional<CommuneDtoVisualisation> findByCodeInsee(String codeInsee);
}
