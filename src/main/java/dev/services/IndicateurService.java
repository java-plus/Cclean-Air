package dev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.controllers.dto.CommuneIndicateurDto;
import dev.controllers.dto.IndicateurDto;
import dev.entities.Indicateur;
import dev.entities.Utilisateur;
import dev.exceptions.NombreIndicateursException;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.repositories.IIndicateurRepository;
import dev.utils.RecuperationUtilisateurConnecte;

/**
 * @author Guillaume Classe regroupant l'intelligence métier liée aux
 *         indicateurs enregistrées par les utilisateurs
 *
 */
@Service
public class IndicateurService {

	/**
	 * Injection du repository
	 */
	private IIndicateurRepository repository;

	/**
	 * Injection de la méthode utilisataire de récupération de l'utilisateur
	 * connecté
	 */
	private RecuperationUtilisateurConnecte recuperationUtilisateurConnecte;

	@Autowired
	public IndicateurService(IIndicateurRepository repository,
			RecuperationUtilisateurConnecte recuperationUtilisateurConnecte) {
		super();
		this.repository = repository;
		this.recuperationUtilisateurConnecte = recuperationUtilisateurConnecte;
	}

	/**
	 * @param Email de l'utilisateur qui va être utilisé pour récupérer les
	 *              indicateurs
	 * @return retourne la liste des noms de communes concernées par les différents
	 *         indicateurs de l'utilisateur
	 */
	public List<CommuneIndicateurDto> recupererLesIndicateurs(String mailUtilisateur) {
		return repository.findByUtilisateurEmail(mailUtilisateur).stream()
				.map(i -> new CommuneIndicateurDto(i.getCommune().getNom())).collect(Collectors.toList());
	}

	/**
	 * Cette méthode persiste un nouvel indicateur en base si l'utilisateur a des
	 * slots d'indicateurs disponible. S'il en dispose déjà de 10, nombre maximal
	 * d'indicateur autorisé, renvoie une exception.
	 * 
	 * @param indicateur : Indicateur à sauvegarder
	 * @return Renvoie l'indicateur nouvellement ajouté
	 * @throws NombreIndicateursException : Exception déclenchée si l'utilisateur
	 *                                    dispose déjà de 10 indicateurs
	 */
	public IndicateurDto sauvegarderNouvelIndicateur(CommuneIndicateurDto indicateur)
			throws NombreIndicateursException {
		Utilisateur utilisateur;
		try {
			utilisateur = recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();
			Indicateur response = new Indicateur();
			response.setUtilisateur(utilisateur);
			if (response.getUtilisateur().getListeIndicateurs().size() < 11) {
				repository.save(response);
				return new IndicateurDto(response.getUtilisateur(), response.getCommune());
			} else {
				throw new NombreIndicateursException("Nombre d'indicateurs autorisés atteint");
			}
		} catch (UtilisateurNonConnecteException e) {
			e.getMessage();
			return null;
		}

	}

}
