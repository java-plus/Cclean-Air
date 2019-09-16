package dev.services;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.controllers.dto.*;
import dev.entities.Commune;
import dev.entities.Statut;
import dev.exceptions.MotDePasseInvalideException;
import dev.exceptions.UtilisateurInvalideException;
import dev.repositories.ICommuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import dev.entities.Indicateur;
import dev.entities.Utilisateur;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.repositories.IUtilisateurRepository;
import dev.utils.RecuperationUtilisateurConnecte;

import javax.transaction.Transactional;

@Service
public class UtilisateurService {

    private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);

    private PasswordEncoder passwordEncoder;
    private IUtilisateurRepository utilisateurRepository;
    private RecuperationUtilisateurConnecte recuperationUtilisateurConnecte;
    private CommuneService communeService;
    private ICommuneRepository communeRepository;

    @Autowired
    public UtilisateurService(PasswordEncoder passwordEncoder, IUtilisateurRepository utilisateurRepository, RecuperationUtilisateurConnecte recuperationUtilisateurConnecte, CommuneService communeService, ICommuneRepository communeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
        this.recuperationUtilisateurConnecte = recuperationUtilisateurConnecte;
        this.communeService = communeService;
        this.communeRepository = communeRepository;
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

    @Transactional
    public void modifierProfil(ProfilModificationPost profilModificationPost) throws UtilisateurNonConnecteException, MotDePasseInvalideException {

        var utilisateur = recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();
        LOGGER.info("Utilisateur : {0}", utilisateur);

        if (profilModificationPost.getNom() != null && !profilModificationPost.getNom().equals("")) {
            utilisateur.setNom(profilModificationPost.getNom());
        }

        if (profilModificationPost.getPrenom() != null && !profilModificationPost.getPrenom().equals("")) {
            utilisateur.setPrenom(profilModificationPost.getPrenom());
        }

        if (profilModificationPost.getEmail() != null && !profilModificationPost.getEmail().equals("")) {
            utilisateur.setEmail(profilModificationPost.getEmail());
        }

        if (profilModificationPost.getCommune() != null && !profilModificationPost.getCommune().equals("")) {
            String nomCommune = profilModificationPost.getCommune();
            Optional<Commune> commune = communeRepository.findByNomIgnoreCase(nomCommune);
            if (commune.isPresent()) {
                utilisateur.setCommune(commune.get());
            }

        }

        List<Indicateur> listeIndicateur = new ArrayList<>();

        List<CommuneIndicateurDto> listPost = profilModificationPost.getListeIndicateurs();

        for (CommuneIndicateurDto indicateursPost: listPost) {

            if(indicateursPost.getAlerte().equals(true)) {
                Indicateur indicateur = new Indicateur();
                indicateur.setCommune(utilisateur.getCommune());
                indicateur.setAlerte(true);
                indicateur.setUtilisateur(utilisateur);
                listeIndicateur.add(indicateur);
            }
        }

        if(listeIndicateur != null){

            utilisateur.setListeIndicateurs(listeIndicateur);
        }


        if (profilModificationPost.getMotDePasseActuel() != null && !profilModificationPost.getMotDePasseActuel().equals("")) {
            if (passwordEncoder.encode(profilModificationPost.getMotDePasseActuel()).equals(passwordEncoder.encode(utilisateur.getMotDePasse()))) {
                if (passwordEncoder.encode(profilModificationPost.getMotDePasseNouveau()).equals(passwordEncoder.encode(profilModificationPost.getGetMotDePasseNouveauValidation()))) {
                    utilisateur.setMotDePasse(profilModificationPost.getGetMotDePasseNouveauValidation());
                } else {
                    throw new MotDePasseInvalideException("Le nouveau mot de passe et sa validation sont différents. ");
                }
            } else {
                throw new MotDePasseInvalideException("Le mot de passe saisi n'est pas le mot de passe actuel");
            }

            utilisateurRepository.save(utilisateur);


        }


    }
}

