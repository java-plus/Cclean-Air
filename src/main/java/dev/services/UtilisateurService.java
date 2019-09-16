package dev.services;

import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.controllers.dto.UtilisateurDtoAdmin;
import dev.entities.Statut;
import dev.exceptions.UtilisateurInvalideException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dev.controllers.dto.CommuneIndicateurDto;
import dev.controllers.dto.ProfilDtoGet;
import dev.controllers.dto.UtilisateurDtoPost;
import dev.entities.Indicateur;
import dev.entities.Utilisateur;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.repositories.IUtilisateurRepository;
import dev.utils.RecuperationUtilisateurConnecte;

@Service
public class UtilisateurService {

    private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);

    private PasswordEncoder passwordEncoder;
    private IUtilisateurRepository utilisateurRepository;
    private RecuperationUtilisateurConnecte recuperationUtilisateurConnecte;
    private CommuneService communeService;

    @Autowired
    public UtilisateurService(PasswordEncoder passwordEncoder, IUtilisateurRepository utilisateurRepository,
                              RecuperationUtilisateurConnecte recuperationUtilisateurConnecte, CommuneService communeService) {
        super();
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
        this.recuperationUtilisateurConnecte = recuperationUtilisateurConnecte;
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
     *
     * @return
     */
    public List<UtilisateurDtoAdmin> creerListeUtilisateur() {
        return utilisateurRepository.findAllwithNomPrenomEmail();
    }

    /**
     * Méthode qui supprime un utilisateur
     * Elle vérifie que la  personne connectée n'est pas un admin qui supprime son propre compte
     *
     * @param email
     */
    public void supprimerUtilisateur(String email) {
        //récupération utilisateur via l'email
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmailIgnoreCase(email);

        //récupération de l'email de la personne connectée et création d'un objet Utilisateur pour l'utilisateur connecté
        String emailConnecte = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilisateur> utilisateurConnecte = utilisateurRepository.findByEmailIgnoreCase(emailConnecte);

        //Vérification du statut de l'utilisateur et suppression si autorisé à surpprimer.
        List<Statut> statut = utilisateurConnecte.get().getStatut();

        if (statut.contains(Statut.ADMINISTRATEUR)) {
            if (!utilisateur.get().getEmail().equals(emailConnecte)) {
                if (utilisateur.isPresent()) {
                    utilisateurRepository.delete(utilisateur.get());
                }
            } else {
                throw new UtilisateurInvalideException("Un admin ne peut pas supprimer son propre compte");
            }
        }

    }
    
    /**
     * Méthode qui permet de supprimer son compte
     * Elle vérifie que la personne connectée n'est pas un admin qui supprime son propre compte
     * 
     * @param email
     */
    public void supprimerComptePerso() {
    	//récupération de l'utilisateur connecté via l'eamil
    	String emailConnecte = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Optional<Utilisateur> utilisateurConnecte = utilisateurRepository.findByEmailIgnoreCase(emailConnecte);
    	
    	//Vérification du statut de l'utilisateur et suppression si autorisé à supprimer.
    	List<Statut> statut = utilisateurConnecte.get().getStatut();
    	
    	if (!statut.contains(Statut.ADMINISTRATEUR)) {
    		if (utilisateurConnecte.get().getEmail().equals(emailConnecte)) {
    			if (utilisateurConnecte.isPresent()) {
        			utilisateurRepository.delete(utilisateurConnecte.get());
        		}
    		}    		
    	} else {
    		throw new UtilisateurInvalideException("Un admin ne peut pas supprimer son propre compte");
    	}
    }

    /**
     * @return renvoie les informations nécessaire à l'affichage de la vue du profil
     * utilisateur via un objet ProfilDtoGet
     * @throws UtilisateurNonConnecteException
     */
    public ProfilDtoGet visualiserProfil() throws UtilisateurNonConnecteException {
        var utilisateur = recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();
        LOGGER.info("Utilisateur : {0}", utilisateur);
        List<CommuneIndicateurDto> listeIndicateurs = utilisateur.getListeIndicateurs().stream()
                .map(i -> new CommuneIndicateurDto(i.getCommune().getNom(), i.getAlerte()))
                .collect(Collectors.toList());

        return new ProfilDtoGet(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(),
                utilisateur.getCommune().getNom(), listeIndicateurs, utilisateur.getStatutNotification(),
                utilisateur.getMotDePasse());

    }


}
