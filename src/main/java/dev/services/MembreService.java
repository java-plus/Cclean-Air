package dev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.controllers.dto.UtilisateurDtoPost;
import dev.entities.Utilisateur;
import dev.repositories.IUtilisateurRepository;

@Service
public class MembreService {

	private IUtilisateurRepository membreRepository;

	@Autowired
	public MembreService(IUtilisateurRepository membreRepository) {
		this.membreRepository = membreRepository;
	}

	/**
	 * Méthode pour sauvegarder un membre dans la base de données.
	 *
	 * @param utilisateur : [UtilisateurDtoPost] le membre à sauvegarder.
	 */
	// TODO : passwordEncoder.encode(utilisateur.getMotDePasse())
	public Utilisateur sauvegarderUtilisateur(UtilisateurDtoPost utilisateur) {
		Utilisateur membre = new Utilisateur(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(),
				utilisateur.getMotDePasse(), utilisateur.getStatutNotification(), 0, null);
		membreRepository.save(membre);
		return membre;
	}
}
