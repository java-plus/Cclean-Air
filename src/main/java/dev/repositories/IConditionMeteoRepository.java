package dev.repositories;

import java.time.ZonedDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.entities.ConditionMeteo;

/**
 * 
 * 
 * @author Cécile Classe Repository faisant le lien avec la table ConditionMeteo
 *         ======= Classe repository faisant le lien avec la base de données et
 *         notamment la table ConditionMeteo. >>>>>>> master
 */
@Repository
public interface IConditionMeteoRepository extends JpaRepository<ConditionMeteo, Integer> {

	@Transactional
	@Modifying
	@Query("delete from ConditionMeteo d where d.date <= ?1")
	void deleteAllExpiredSince(ZonedDateTime zonedDateTime);

}
