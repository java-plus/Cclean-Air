package dev.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.controllers.dto.UtilisateurDtoAdmin;
import dev.entities.Statut;
import dev.exceptions.UtilisateurInvalideException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.controllers.dto.UtilisateurDtoPost;
import dev.entities.Indicateur;
import dev.entities.Utilisateur;
import dev.repositories.IUtilisateurRepository;

@Service
public class UtilisateurService {

	private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);

	private PasswordEncoder passwordEncoder;
	private IUtilisateurRepository utilisateurRepository;
	private CommuneService communeService;

	@Autowired
	public UtilisateurService(PasswordEncoder passwordEncoder, IUtilisateurRepository utilisateurRepository,
			CommuneService communeService) {
		this.passwordEncoder = passwordEncoder;
		this.utilisateurRepository = utilisateurRepository;
		this.communeService = communeService;
	}

	/**
	 * Méthode vérifiant si l'email est utilisé par un compte dans la base de
	 * données.
	 * 
	 * @param email : email à vérifier
	 * @return true (email existant) ou false (email non utilisé)
	 */
	public boolean isEmailExistant(String email) {
		LOGGER.info("isEmailExistant() lancé / email =" + email);
		return utilisateurRepository.findByEmailIgnoreCase(email).isPresent();
	}

	/**
	 * Méthode pour sauvegarder un utilisateur dans la base de données.
	 * 
	 * @param dto : [UtilisateurDtoPost] l'utilisateur à sauvegarder.
	 */
	public Utilisateur sauvegarderUtilisateur(UtilisateurDtoPost dto) {
		Utilisateur utilisateur = new Utilisateur(dto.getNom(), dto.getPrenom(), dto.getEmail(),
				passwordEncoder.encode(dto.getMotDePasse()), dto.getStatuts(), dto.getStatutNotification(), 0,
				ZonedDateTime.now(), new ArrayList<Indicateur>(), communeService.recupererCommune(dto.getNomCommune()));
		utilisateurRepository.save(utilisateur);
		return utilisateur;

	}

	/**
	 * Méthode qui retourne la liste des utilisateurs avec le nom et leur prénom
	 * @return
	 */
	public List<UtilisateurDtoAdmin> creerListeUtilisateur(){
		return  utilisateurRepository.findAllwithNomPrenomEmail();
	}

	public void supprimerUtilisateur(String email)  {
		Optional <Utilisateur> utilisateur = utilisateurRepository.findByEmailIgnoreCase(email);

		String emailConnecte = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Optional<Utilisateur> utilisateurConnecte = utilisateurRepository.findByEmailIgnoreCase(emailConnecte);

		List<Statut> statut = utilisateurConnecte.get().getStatut();

		if(statut.contains(Statut.ADMINISTRATEUR)){
			if(!utilisateur.get().getEmail().equals(emailConnecte)){
				if(utilisateur.isPresent()){
					utilisateurRepository.delete(utilisateur.get());
				}
			} else {
				throw new UtilisateurInvalideException("Un admin ne peut pas supprimer son propre compte");
			}
		}

	}
}
