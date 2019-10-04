package dev.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.controllers.dto.CommuneDto;
import dev.controllers.dto.CommuneDtoGetLight;
import dev.entities.Commune;

/**
 * Classe repository faisant le lien avec la base de données et notamment la
 * table commune.
 */
@Repository
public interface ICommuneRepository extends JpaRepository<Commune, Integer> {

	Optional<Commune> findByNomIgnoreCase(String nomCommune);

	Optional<Commune> findByCodeInsee(String codeInsee);

	@Query("select new dev.controllers.dto.CommuneDto(c.nom, c.nbHabitants, c.codeInsee, c.latitude, c"
			+ ".longitude) from Commune c")
	List<CommuneDto> findAllWithCodeDenomination();

	@Query("select new dev.controllers.dto.CommuneDtoGetLight(c.nom, c"
			+ ".nbHabitants, c.codeInsee, c.latitude, c.longitude) from Commune"
			+ " c where c.codeInsee LIKE '44%' order by c.nom")
	List<CommuneDtoGetLight> findAllDtoGetLight();

}
