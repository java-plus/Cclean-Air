package dev.services;

import dev.controllers.dto.UtilisateurDtoPost;
import dev.entities.Membre;
import dev.repositories.IMembreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembreService {

    private IMembreRepository membreRepository;

    @Autowired
    public MembreService(IMembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    /**
     * Méthode pour sauvegarder un membre dans la base de données.
     *
     * @param utilisateur : [UtilisateurDtoPost] le membre à sauvegarder.
     */
    // TODO : passwordEncoder.encode(utilisateur.getMotDePasse())
    public Membre sauvegarderMembre(UtilisateurDtoPost utilisateur) {
        Membre membre = new Membre(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(),
                utilisateur.getMotDePasse(), utilisateur.getStatutNotification(), 0, null);
        membreRepository.save(membre);
        return membre;
    }
}
