package dev.repositories;

import dev.entities.QualiteAir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Cécile
 * Classe Repository faisant le lien avec la table QualiteAir
 */
@Repository
public interface IQualiteAirRepository extends JpaRepository<QualiteAir, Integer> {


}

