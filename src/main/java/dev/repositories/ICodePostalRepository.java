package dev.repositories;

import dev.entities.CodePostal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe repository faisant le lien avec la base de données et notamment la table code_postal.
 */
public interface ICodePostalRepository extends JpaRepository<CodePostal, Integer> {

}
