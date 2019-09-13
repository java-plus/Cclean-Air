package dev.repositories;

import dev.entities.DonneesLocales;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe faisant l'interface avec la base de données et la table DonneesLocales spécifiquement.
 */
public interface IDonneesLocalesRepository extends JpaRepository<DonneesLocales, Integer> {

}
