package dev.services;

import dev.controllers.dto.UtilisateurDtoPost;
import dev.repositories.IAdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe regroupant les services d'un administrateur.
 */
@Service
public class AdministrateurService {

    private IAdministrateurRepository administrateurRepository;

    @Autowired
    public AdministrateurService(IAdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;
    }

    /**
     * Méthode pour sauvegarder un administrateur dans la base de données.
     * @param utilisateur : [UtilisateurDtoPost] l'administrateur à sauvegarder.
     */
    // TODO : passwordEncoder.encode(administrateur.getMotDePasse())
    public Administrateur sauvegarderAdministrateur(UtilisateurDtoPost utilisateur) {
        Administrateur admin = new Administrateur(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(),
                utilisateur.getMotDePasse(), utilisateur.getStatutNotification(), 0);
        administrateurRepository.save(admin);
        return admin;
    }

}
