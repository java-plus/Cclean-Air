
package dev.repositories;

import dev.controllers.dto.ProfilDtoGet;
import dev.controllers.dto.ProfilModifcationGet;
import dev.controllers.dto.UtilisateurDtoAdmin;
import dev.controllers.dto.UtilisateurDtoGet;
import dev.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         communes aux profils membre et utilisateur
 *
 */
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	/**
	 * Méthode qui retourne un optional d'un utilisateur à partir de son email.
	 * @param email : String
	 * @return : Optional<Utilisateur>
	 */
	Optional<Utilisateur> findByEmailIgnoreCase(String email);

	@Query("select new dev.controllers.dto.UtilisateurDtoAdmin(u.nom, u.prenom, u.email) from Utilisateur u")
	List<UtilisateurDtoAdmin> findAllwithNomPrenomEmail();

}
