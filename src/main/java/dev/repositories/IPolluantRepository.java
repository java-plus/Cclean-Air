package dev.repositories;

import dev.controllers.dto.visualiserDonnees.PolluantDtoVisualisation;
import dev.entities.Polluant;
import dev.entities.QualiteAir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Cécile Classe Repository faisant le lien avec la table Polluant
 */
@Repository
public interface IPolluantRepository extends JpaRepository<Polluant, Integer> {

	@Query("select new dev.controllers.dto.visualiserDonnees.PolluantDtoVisualisation(p.nom, p.valeur, p.unite) from Polluant p")
	List<PolluantDtoVisualisation> findByQualiteAir(QualiteAir qualiteAir);

	@Transactional
	@Modifying
	@Query("delete from Polluant p where p.qualiteAir = ?1")
	void supprimerPolluantsDeLaQualiteAirIndiquee(QualiteAir qualiteAir);
}
