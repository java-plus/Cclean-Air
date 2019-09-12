package dev.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.controllers.dto.CommuneIndicateurDto;
import dev.controllers.dto.IndicateurDto;
import dev.entities.Commune;
import dev.entities.Indicateur;
import dev.entities.Utilisateur;
import dev.exceptions.CommuneInvalideException;
import dev.exceptions.NombreIndicateursException;
import dev.exceptions.UtilisateurNonConnecteException;
import dev.repositories.ICommuneRepository;
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

	private ICommuneRepository communeRepository;

	/**
	 * Injection de la méthode utilisataire de récupération de l'utilisateur
	 * connecté
	 */
	private RecuperationUtilisateurConnecte recuperationUtilisateurConnecte;

	@Autowired
	public IndicateurService(IIndicateurRepository repository, ICommuneRepository communeRepository,
			RecuperationUtilisateurConnecte recuperationUtilisateurConnecte) {
		super();
		this.repository = repository;
		this.communeRepository = communeRepository;
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
			Optional<Commune> commune = communeRepository.findByNomIgnoreCase(indicateur.getCommune());
			if (commune.isPresent()) {
				response.setCommune(commune.get());
			} else {
				throw new CommuneInvalideException("Commune invalide");
			}

			if (response.getUtilisateur().getListeIndicateurs().size() < 11) {
				repository.save(response);
				return new IndicateurDto(response.getUtilisateur().getEmail(), response.getCommune().getNom());
			} else {
				throw new NombreIndicateursException("Nombre d'indicateurs autorisés atteint");
			}
		} catch (UtilisateurNonConnecteException e) {
			e.getMessage();
			return null;
		}

	}

	/**
	 * @param indicateur : Nom de la commune représentant l'indicateur à supprimer
	 * @return renvoie le mail tilisateur et le nom de la commune à supprimer
	 */
	public IndicateurDto supprimerUnIndicateur(CommuneIndicateurDto indicateur) {
		try {
			List<Indicateur> indicateurs = repository
					.findByUtilisateurEmail(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail());
			List<Indicateur> indicateursFiltrés = indicateurs.stream()
					.filter(i -> i.getCommune().getNom().equals(indicateur.getCommune())).collect(Collectors.toList());
			Indicateur suppression = null;
			if (!indicateursFiltrés.isEmpty()) {
				suppression = indicateursFiltrés.get(0);
				repository.delete(suppression);
				return new IndicateurDto(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail(),
						suppression.getCommune().getNom());
			}
			return null;

		} catch (UtilisateurNonConnecteException e) {
			e.getMessage();
			return null;
		}
	}

}
