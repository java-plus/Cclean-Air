package dev.services;

import dev.controllers.dto.CommuneNiveauAlerteDto;
import dev.controllers.dto.NiveauAlerteDto;
import dev.entities.Commune;
import dev.entities.Polluant;
import dev.exceptions.DonneesLocalesException;
import dev.exceptions.UtilisateurNonConnecteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe regroupant les méthode de services liées aux alertes pollution.
 *
 * @author Guillaume & JB
 */
@Service
public class AlerteService {

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * @param commune Commune sur laquelle seront vérifiés les niveaux d'alerte
     * @return valorise un objet de type NiveauAlerteDto si l'un des seuils est
     * franchi, sinon renvoi null
     */
    public NiveauAlerteDto donnerNiveauAlerte(Commune commune) {
        if(commune.getDonneesLocales().isEmpty()) {
            throw new DonneesLocalesException("Erreur : erreur dans la " +
                    "récupération des données locales.");
        }

        List<Polluant> listePolluantsCommune =
                commune.getDonneesLocales().get(0).getQualiteAir().getListePolluants();

        List<Polluant> listeParticulesFines = listePolluantsCommune.stream()
                .filter(p -> p.getNom().equals("Particules fines")).collect(Collectors.toList());
        if (listeParticulesFines.stream().anyMatch(p -> p.getValeur() >= 80.0)) {
            return new NiveauAlerteDto("Particules fines",
                    listeParticulesFines.get(0).getValeur());
        }

        List<Polluant> listeOzone =
                listePolluantsCommune.stream().filter(p -> p.getNom().equals(
                        "Ozone"))
                        .collect(Collectors.toList());
        if (listeOzone.stream().anyMatch(p -> p.getValeur() >= 240.0)) {
            return new NiveauAlerteDto("Ozone", listeOzone.get(0).getValeur());
        }

        List<Polluant> listeDioxydeAzote = listePolluantsCommune.stream()
                .filter(p -> p.getNom().equals("Dioxyde d'azote")).collect(Collectors.toList());
        if (listeDioxydeAzote.stream().anyMatch(p -> p.getValeur() >= 200.0)) {
            return new NiveauAlerteDto("Dioxyde d'azote",
                    listeDioxydeAzote.get(0).getValeur());
        }

        return null;
    }

    /**
     * Teste chaque commune pour lesquelles l'utilisateur veut être notifié
     * s'il y a une alerte et renvoie une liste d'alertes pollution.
     *
     * @return List<CommuneNiveauAlerteDto> : la liste des alertes (commune
     * concernée, nom du polluant et valeur du polluant)
     * @throws UtilisateurNonConnecteException : exception si utilisateur non
     *                                         authentifié
     *
     */
    public List<CommuneNiveauAlerteDto> verifierAlertesUtilisateurVeutNotification()
            throws UtilisateurNonConnecteException {

        List<Commune> communes =
                utilisateurService.recupererCommunesUtilisateurCourantVeutNotifications();

        List<CommuneNiveauAlerteDto> listeAlertes = new ArrayList<>();

        for (Commune commune : communes) {

            NiveauAlerteDto niveauAlerteDto = donnerNiveauAlerte(commune);

            if (niveauAlerteDto != null) {
                listeAlertes.add(new CommuneNiveauAlerteDto(commune.getNom(),
                        niveauAlerteDto.getNomPolluant(),
                        niveauAlerteDto.getValeur(), commune.getCodeInsee()));
            }
        }

        return listeAlertes;

    }

    /**
     * Pour chacune des communes pour lesquelles l'utilisateur a un
     * indicateur, retourne l'alerte pollution s'il y en a une.
     *
     * @return List<CommuneNiveauAlerteDto> : la liste des alertes (commune
     * concernée, nom du polluant et valeur du polluant)
     * @throws UtilisateurNonConnecteException : exception si utilisateur non
     *                                         authentifié
     */
    public List<CommuneNiveauAlerteDto> verifierAlertesUtilisateurTousIndicateurs()
            throws UtilisateurNonConnecteException {

        List<Commune> communes =
                utilisateurService.recupererCommunesUtilisateurAvecIndicateur();

        List<CommuneNiveauAlerteDto> listeAlertes = new ArrayList<>();

        for (Commune commune : communes) {

            NiveauAlerteDto niveauAlerteDto = donnerNiveauAlerte(commune);

            if (niveauAlerteDto != null) {
                listeAlertes.add(new CommuneNiveauAlerteDto(commune.getNom(),
                        niveauAlerteDto.getNomPolluant(),
                        niveauAlerteDto.getValeur(), commune.getCodeInsee()));
            }
        }

        return listeAlertes;
    }

}
