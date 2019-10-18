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

    @GetMapping()
    public List<CommuneNiveauAlerteDto> afficherAlertesANotifierPourUtilisateur()
            throws UtilisateurNonConnecteException {

        LOGGER.info("affichage des alertes à notifier pour l'utilisateur " +
                "connecté");

        return alerteService.verifierAlertesUtilisateur();
    }

}
