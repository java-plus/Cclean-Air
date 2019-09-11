package dev.services;

import dev.repositories.IAdministrateurRepository;
import dev.repositories.IMembreRepository;
import dev.repositories.IUtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private final Logger LOGGER = LoggerFactory.getLogger(UtilisateurService.class);

    private IUtilisateurRepository utilisateurRepository;
    private IAdministrateurRepository administrateurRepository;
    private IMembreRepository membreRepository;

    @Autowired
    public UtilisateurService(IUtilisateurRepository utilisateurRepository,
                              IAdministrateurRepository administrateurRepository, IMembreRepository membreRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.administrateurRepository = administrateurRepository;
        this.membreRepository = membreRepository;
    }

    /**
     * Méthode vérifiant si l'email est utilisé par un compte dans la base de données.
     * @param email : email à vérifier
     * @return true (email existant) ou false (email non utilisé)
     */
    public boolean isEmailExistant(String email) {
        LOGGER.info("isEmailExistant() lancé / email =" + email);
        return administrateurRepository.findByEmailIgnoreCase(email).isPresent() || membreRepository.findByEmailIgnoreCase(email).isPresent();
    }

}
