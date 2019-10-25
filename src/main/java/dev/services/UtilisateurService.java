package dev.services;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import dev.controllers.dto.*;
import dev.entities.Commune;
import dev.entities.Indicateur;
import dev.entities.Statut;
import dev.entities.Utilisateur;
import dev.exceptions.EmailInvalideException;
import dev.exceptions.MotDePasseInvalideException;
import dev.exceptions.UtilisateurInvalideException;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.repositories.ICommuneRepository;
import dev.repositories.IUtilisateurRepository;
import dev.utils.MailUtils;
import dev.utils.RecuperationUtilisateurConnecte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Cécile Peyras
 */
@Service
public class UtilisateurService {

    private final Logger LOGGER =
            LoggerFactory.getLogger(UtilisateurService.class);

    private PasswordEncoder passwordEncoder;
    private IUtilisateurRepository utilisateurRepository;
    private RecuperationUtilisateurConnecte recuperationUtilisateurConnecte;
    private CommuneService communeService;
    private ICommuneRepository communeRepository;
    private MailUtils mailUtils;

    @Autowired
    public UtilisateurService(PasswordEncoder passwordEncoder,
                              IUtilisateurRepository utilisateurRepository,
                              RecuperationUtilisateurConnecte recuperationUtilisateurConnecte, CommuneService communeService,
                              ICommuneRepository communeRepository,
                              MailUtils mailUtils) {
        this.passwordEncoder = passwordEncoder;
        this.utilisateurRepository = utilisateurRepository;
        this.recuperationUtilisateurConnecte = recuperationUtilisateurConnecte;
        this.communeService = communeService;
        this.communeRepository = communeRepository;
        this.mailUtils = mailUtils;
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
        Utilisateur utilisateur = new Utilisateur(dto.getNom(),
                dto.getPrenom(), dto.getEmail(),
                passwordEncoder.encode(dto.getMotDePasse()), dto.getStatuts()
                , dto.getStatutNotification(), 0,
                ZonedDateTime.now(), new ArrayList<Indicateur>(),
                communeService.recupererCommune(dto.getNomCommune()));
        utilisateurRepository.save(utilisateur);
        return utilisateur;

    }

    /**
     * Méthode qui retourne la liste des utilisateurs avec le nom et leur
     * prénom
     *
     * @return
     */
    public List<UtilisateurDtoAdmin> creerListeUtilisateur() {
        return utilisateurRepository.findAllwithNomPrenomEmail();
    }

    /**
     * Méthode qui supprime un utilisateur Elle vérifie que la personne
     * connectée
     * n'est pas un admin qui supprime son propre compte
     *
     * @param email
     */
    public void supprimerUtilisateur(String email) {
        // récupération utilisateur via l'email
        Optional<Utilisateur> utilisateur =
                utilisateurRepository.findByEmailIgnoreCase(email);

        // récupération de l'email de la personne connectée et création d'un
        // objet
        // Utilisateur pour l'utilisateur connecté
        String emailConnecte =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilisateur> utilisateurConnecte =
                utilisateurRepository.findByEmailIgnoreCase(emailConnecte);

        // Vérification du statut de l'utilisateur et suppression si autorisé à
        // surpprimer.
        List<Statut> statut = utilisateurConnecte.get().getStatut();

        if (statut.contains(Statut.ADMINISTRATEUR)) {
            if (!utilisateur.get().getEmail().equals(emailConnecte)) {
                if (utilisateur.isPresent()) {
                    utilisateurRepository.delete(utilisateur.get());
                }
            } else {
                throw new UtilisateurInvalideException("Un admin ne peut pas " +
                        "supprimer son propre compte");
            }
        }
    }

    /**
     * Méthode qui permet de supprimer son compte Elle vérifie que la personne
     * connectée n'est pas un admin qui supprime son propre compte
     *
     * @param email
     */
    public void supprimerComptePerso() {
        // récupération de l'utilisateur connecté via l'eamil
        String emailConnecte =
                (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Utilisateur> utilisateurConnecte =
                utilisateurRepository.findByEmailIgnoreCase(emailConnecte);

        // Vérification du statut de l'utilisateur et suppression si autorisé à
        // supprimer.
        List<Statut> statut = utilisateurConnecte.get().getStatut();

        if (!statut.contains(Statut.ADMINISTRATEUR)) {
            if (utilisateurConnecte.get().getEmail().equals(emailConnecte)) {
                if (utilisateurConnecte.isPresent()) {
                    utilisateurRepository.delete(utilisateurConnecte.get());
                }
            }
        } else {
            throw new UtilisateurInvalideException("Un admin ne peut pas " +
                    "supprimer son propre compte");
        }
    }

    /**
     * @return renvoie les informations nécessaire à l'affichage de la vue du
     * profil
     * utilisateur via un objet ProfilDtoGet
     * @throws UtilisateurNonConnecteException
     */
    public ProfilDtoGet visualiserProfil() throws UtilisateurNonConnecteException {
        var utilisateur =
                recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();
        LOGGER.info("Utilisateur : {0}", utilisateur);
        List<CommuneIndicateurDto> listeIndicateurs =
                utilisateur.getListeIndicateurs().stream()
                        .map(i -> new CommuneIndicateurDto(i.getCommune().getNom(),
                                i.getAlerte()))
                        .collect(Collectors.toList());

        return new ProfilDtoGet(utilisateur.getNom(), utilisateur.getPrenom()
                , utilisateur.getEmail(),
                utilisateur.getCommune().getNom(), listeIndicateurs,
                utilisateur.getStatutNotification(),
                utilisateur.getMotDePasse());

    }

    /**
     * méthode qui permet de modifier le profil de l'utilisateur
     *
     * @param profilModificationPost
     * @return
     * @throws UtilisateurNonConnecteException
     * @throws MotDePasseInvalideException
     */
    public ProfilModifcationGet modifierProfil(ProfilModificationPost profilModificationPost)
            throws UtilisateurNonConnecteException,
            MotDePasseInvalideException, EmailInvalideException {

        // Récupération de l'utilisateur
        var utilisateur =
                recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();
        LOGGER.info("Utilisateur : {0}", utilisateur);

        // modification du nom
        if (profilModificationPost.getNom() != null && !profilModificationPost.getNom().equals("")) {
            utilisateur.setNom(profilModificationPost.getNom());
        }

        // modification du prénom
        if (profilModificationPost.getPrenom() != null && !profilModificationPost.getPrenom().equals("")) {
            utilisateur.setPrenom(profilModificationPost.getPrenom());
        }

        // modification de l'email
        if (profilModificationPost.getEmail() != null && !profilModificationPost.getEmail().equals("")) {
            // validation de l'email
            utilisateur.setEmail(profilModificationPost.getEmail());
        }

        // modification de la commune
        if (profilModificationPost.getCommune() != null && !profilModificationPost.getCommune().equals("")) {
            String nomCommune = profilModificationPost.getCommune();
            Optional<Commune> commune =
                    communeRepository.findByNomIgnoreCase(nomCommune);
            if (commune.isPresent()) {
                utilisateur.setCommune(commune.get());
            }
        }

        // modification du statut notification
        if (profilModificationPost.getstatutNotification() != null) {
            utilisateur.setStatutNotification(profilModificationPost.getstatutNotification());
        } else {
            if (profilModificationPost.getstatutNotification() == null) {
                utilisateur.setStatutNotification(false);
            }
        }

        // modification de la liste d'indicateurs
        List<Indicateur> listeIndicateur = new ArrayList<>();
        List<CommuneIndicateurDto> listPost =
                profilModificationPost.getListeIndicateurs();
        List<CommuneIndicateurDto> listGet = new ArrayList<>();
        if (listPost != null) {
            for (CommuneIndicateurDto indicateursPost : listPost) {

                if (indicateursPost.getAlerte()) {
                    Indicateur indicateur = new Indicateur();
                    indicateur.setCommune(utilisateur.getCommune());
                    indicateur.setAlerte(true);
                    indicateur.setUtilisateur(utilisateur);
                    listeIndicateur.add(indicateur);
                    CommuneIndicateurDto communeIndicateurDto =
                            new CommuneIndicateurDto();
                    communeIndicateurDto.setAlerte(true);
                    communeIndicateurDto.setCommune(utilisateur.getCommune().getNom());
                    listGet.add(communeIndicateurDto);

                }
            }
            if (listeIndicateur != null) {

                utilisateur.setListeIndicateurs(listeIndicateur);
            }
        }

        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=" +
                ".*?[#?!@$%^&*-]).{7,}$";

        // modification du mot de passe
        if (profilModificationPost.getMotDePasseActuel() != null
                && !profilModificationPost.getMotDePasseActuel().equals("")) {
            // vérification de la correspondance des mots de passe pour
            // modification
            if (passwordEncoder.matches(profilModificationPost.getMotDePasseActuel(), utilisateur.getMotDePasse())) {
                if (profilModificationPost.getMotDePasseNouveau().matches(regex)
                        && profilModificationPost.getMotDePasseNouveau().length() >= 7) {
                    if (profilModificationPost.getMotDePasseNouveau()
                            .equals(profilModificationPost.getGetMotDePasseNouveauValidation())) {
                        utilisateur.setMotDePasse(
                                passwordEncoder.encode(profilModificationPost.getGetMotDePasseNouveauValidation()));
                    } else {
                        throw new MotDePasseInvalideException(
                                "Le nouveau mot de passe et sa validation " +
                                        "sont différents.");
                    }
                } else {
                    throw new MotDePasseInvalideException(
                            "Le mot de passe doit contenir une majuscule, une" +
                                    " minuscule, un chiffre, un caractère " +
                                    "spécial et doit contenir minimum 7 " +
                                    "caractères");
                }
            } else {
                throw new MotDePasseInvalideException("Le mot de passe saisi " +
                        "n'est pas le mot de passe actuel");
            }
        }
        utilisateurRepository.save(utilisateur);

        return new ProfilModifcationGet(utilisateur.getNom(),
                utilisateur.getPrenom(), utilisateur.getEmail(),
                utilisateur.getStatutNotification(),
                utilisateur.getCommune().getNom(), listGet);

    }

    /**
     * méthode qui valide que l'on est connecté en admin et qui renvoie true si
     * c'est bien le cas
     *
     * @return
     * @throws UtilisateurNonConnecteException
     */
    public Boolean validationAdmin() throws UtilisateurNonConnecteException {
        var utilisateur =
                recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();

        if (utilisateur.getStatut().contains(Statut.ADMINISTRATEUR)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Méthode renvoyant la liste des communes pour lesquelles l'utilisateur
     * authentifié souhaite recevoir des notifications
     *
     * @return List<Commune> : liste des communes pour lesquelles une
     * notification est souhaitée en cas d'alerte pollution
     * @throws UtilisateurNonConnecteException : exception si aucun
     *                                         utilisateur n'est authentifié
     */
    public List<Commune> recupererCommunesUtilisateurCourantVeutNotifications()
            throws UtilisateurNonConnecteException {
        var utilisateur =
                recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();

        List<Commune> communes = utilisateur.getListeIndicateurs()
                .stream().filter(Indicateur::getAlerte)
                .map(Indicateur::getCommune).collect(Collectors.toList());

        if (utilisateur.getStatutNotification().equals(Boolean.TRUE) && utilisateur.getCommune() != null) {
            communes.add(utilisateur.getCommune());
        }

        return communes;
    }

    /**
     * Méthode renvoyant la liste des communes ajoutées en aux indicateurs de
     * l'utilisateur.
     *
     * @return List<Commune> : liste des communes pour lesquelles
     * l'utilisateur a créé un indicateur.
     * @throws UtilisateurNonConnecteException : exception si aucun
     *                                         utilisateur n'est authentifié
     */
    public List<Commune> recupererCommunesUtilisateurAvecIndicateur()
            throws UtilisateurNonConnecteException {
        var utilisateur =
                recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();

        List<Commune> communes = utilisateur.getListeIndicateurs()
                .stream().map(Indicateur::getCommune).collect(Collectors.toList());


        if (utilisateur.getCommune() != null) {
            communes.add(utilisateur.getCommune());
        }

        return communes;
    }

    /**
     * Retourne tous les utilisateurs qui souhaitent être notifiés pour les
     * alertes pollution de la commune dont l'insee est renseignée
     * @param inseeCommune : insee de la commune
     * @return List<Utilisateur> : la liste des utilisateurs voulant être
     * notifiés
     */
    public List<Utilisateur> recupererUtilisateursSouhaitantNotificationPourCommune(String inseeCommune) {

        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        List<Utilisateur> utilisateursSouhaitantNotif = new ArrayList<>();

        for (Utilisateur utilisateur : utilisateurs) {

            List<Indicateur> indicateursConcernes =
                    utilisateur.getListeIndicateurs()
                            .stream()
                            .filter(indicateur -> indicateur.getCommune()
                                    .getCodeInsee().equalsIgnoreCase(inseeCommune))
                            .collect(Collectors.toList());

            if (!indicateursConcernes.isEmpty()) {
                utilisateursSouhaitantNotif.add(utilisateur);
            } else {
                LOGGER.info("else de " +
                        "recupererUtilisateursSouhaitantNotificationPourCommune" + utilisateur.getEmail());
            }
        }

        return utilisateursSouhaitantNotif;
    }

    /**
     * Sollicite une méthode utilisant Mailjet pour envoyer un email à chaque
     * utilisateur souhaitant être notifié sur la commune dont le code insee
     * est renseigné.
     * @param email : l'objet email à envoyer
     * @throws MailjetSocketTimeoutException : exception lancée en cas
     * d'erreur d'envoi des emails
     * @throws MailjetException : exception lancée en cas
     *      * d'erreur d'envoi des emails
     */
    public void envoyerAlerteParEmail(EmailAlerteDto email)
            throws MailjetSocketTimeoutException, MailjetException {

        List<Utilisateur> listeUtilisateurs =
                recupererUtilisateursSouhaitantNotificationPourCommune(email.getCommuneInsee());

        for (Utilisateur utilisateur : listeUtilisateurs) {
            mailUtils.envoyerEmail(utilisateur.getPrenom(), email.getObjet(),
                    utilisateur.getEmail(), email.getCorpsMsg());
        }
    }
}
