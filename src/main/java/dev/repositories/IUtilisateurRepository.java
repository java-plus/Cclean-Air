package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import dev.entities.Utilisateur;

/**
 * @author Guillaume Repository pour les requêtes d'accès à la base de données
 *         communes aux profils membre et utilisateur
 *
 */
@NoRepositoryBean
public interface IUtilisateurRepository<T extends Utilisateur> extends JpaRepository<T, Integer> {

}
