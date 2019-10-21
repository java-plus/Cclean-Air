package dev.controllers;

import dev.controllers.dto.CommuneNiveauAlerteDto;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.services.AlerteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("alertes")
public class AlerteController {

    private Logger LOGGER = LoggerFactory.getLogger(AlerteController.class);

    private AlerteService alerteService;

    @Autowired
    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    /**
     * Contrôleur gérant la requête GET retournant la liste des alertes à
     * notifier pour l'utilisateur courant.
     * @return List<CommuneNiveauAlerteDto> : liste des alertes à notifier
     * pour l'utilisateur courant
     * @throws UtilisateurNonConnecteException : exception lancée si
     * utilisateur courant non reconnu
     */
    @GetMapping()
    public List<CommuneNiveauAlerteDto> afficherAlertesPourIndicateursDeUtilisateur()
            throws UtilisateurNonConnecteException {

        LOGGER.info("affichage des alertes en cours pour les indicateurs de " +
                "l'utilisateur");

        return alerteService.verifierAlertesUtilisateurTousIndicateurs();
    }

    /**
     * Contrôleur gérant la requête GET retournant la liste des alertes pour
     * tous les indicateurs de l'utilisateur courant.
     * @return List<CommuneNiveauAlerteDto> : liste des alertes pour tous les
     * indicateurs de l'utilisateur courant
     * @throws UtilisateurNonConnecteException : exception lancée si
     * utilisateur courant non reconnu
     */
    @GetMapping("/notifications")
    public List<CommuneNiveauAlerteDto> afficherAlertesANotifierPourUtilisateur()
            throws UtilisateurNonConnecteException {

        LOGGER.info("affichage des alertes à notifier pour l'utilisateur " +
                "connecté");

        return alerteService.verifierAlertesUtilisateurVeutNotification();
    }

}
