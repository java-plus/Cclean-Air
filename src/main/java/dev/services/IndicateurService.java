package dev.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.controllers.dto.CommuneIndicateurDto;
import dev.controllers.dto.IndicateurAffichageDto;
import dev.controllers.dto.IndicateurDto;
import dev.entities.Commune;
import dev.entities.Indicateur;
import dev.entities.Utilisateur;
import dev.exceptions.AucuneDonneeException;
import dev.exceptions.CommuneDejaSuivieException;
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
	 * @param @Email de l'utilisateur qui va être utilisé pour récupérer les
	 *               indicateurs
	 * @return retourne la liste des noms de communes concernées par les différents
	 *         indicateurs de l'utilisateur
	 */
	public List<IndicateurAffichageDto> recupererLesIndicateurs(String mailUtilisateur) {
		return repository.findByUtilisateurEmail(mailUtilisateur).stream().map(
				i -> new IndicateurAffichageDto(i.getCommune().getNom(), i.getCommune().getCodeInsee(), i.getAlerte()))
				.collect(Collectors.toList());
	}

	/**
	 * Cette méthode persiste un nouvel indicateur en base si l'utilisateur a des
	 * slots d'indicateurs disponible. S'il en dispose déjà de 10, nombre maximal
	 * d'indicateur autorisé, renvoie une exception.
	 * 
	 * @param indicateur : Indicateur à sauvegarder
	 * @return Renvoie l'indicateur nouvellement ajouté
	 * @throws NombreIndicateursException      : Exception déclenchée si
	 *                                         l'utilisateur dispose déjà de 10
	 *                                         indicateurs
	 * @throws CommuneDejaSuivieException
	 * @throws UtilisateurNonConnecteException
	 */
	public IndicateurDto sauvegarderNouvelIndicateur(CommuneIndicateurDto indicateur)
			throws NombreIndicateursException, UtilisateurNonConnecteException, CommuneDejaSuivieException {
		Utilisateur utilisateur;

		if (!verifierDoublonIndicateur(new CommuneIndicateurDto(indicateur.getCommune(), true))) {
			throw new CommuneDejaSuivieException("Commune déjà présente dans la liste des indicateurs.");
		}

		try {
			utilisateur = recuperationUtilisateurConnecte.recupererUtilisateurViaEmail();
			Indicateur response = new Indicateur();
			response.setUtilisateur(utilisateur);
			response.setAlerte(indicateur.getAlerte());
			Optional<Commune> commune = communeRepository.findByNomIgnoreCase(indicateur.getCommune());
			if (commune.isPresent()) {
				response.setCommune(commune.get());
			} else {
				throw new CommuneInvalideException("Commune invalide");
			}

			if (response.getUtilisateur().getListeIndicateurs().size() < 11) {
				repository.save(response);
				return new IndicateurDto(response.getUtilisateur().getEmail(), response.getCommune().getNom(),
						response.getAlerte());
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
	 * @throws UtilisateurNonConnecteException
	 */
	public IndicateurDto supprimerUnIndicateur(String nomCommune) throws UtilisateurNonConnecteException {

		List<Indicateur> indicateurs = repository
				.findByUtilisateurEmail(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail());
		List<Indicateur> indicateursFiltres = indicateurs.stream()
				.filter(i -> i.getCommune().getNom().equals(nomCommune)).collect(Collectors.toList());
		Indicateur suppression = null;
		if (!indicateursFiltres.isEmpty()) {
			suppression = indicateursFiltres.get(0);
			repository.delete(suppression);
			return new IndicateurDto(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail(),
					suppression.getCommune().getNom(), suppression.getAlerte());
		}
		return null;

	}

	/**
	 * @param @nouvelIndicateur L'indicateur a modifier en base
	 * @return renvoie l'indicateur modifié
	 * @throws UtilisateurNonConnecteException
	 * @throws CommuneDejaSuivieException
	 * @throws AucuneDonneeException
	 */
	public CommuneIndicateurDto modifierIndicateur(CommuneIndicateurDto indicateurs, String nomCommune)
			throws UtilisateurNonConnecteException, CommuneDejaSuivieException, AucuneDonneeException {

		if (indicateurs == null) {
			throw new AucuneDonneeException("Aucune donnée n'a été renseigné");
		}

		Optional<Commune> verificationCommune = communeRepository.findByNomIgnoreCase(nomCommune);
		if (!verificationCommune.isPresent()) {
			throw new CommuneInvalideException("Commune invalide");
		}

		// Recherche l'indicateur utilisateur à modifier
		Indicateur indicateurAModifier = repository
				.findByUtilisateurEmail(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail())
				.stream().filter(i -> i.getCommune().getNom().equals(nomCommune)).collect(Collectors.toList()).get(0);

		// Vérifie si le nouvel indicateur ne créérait pas de doublon dans la liste
		// existante de l'utilisateur. Si ok, on le modifie puis on retourne un objet
		// dto avec le mail de l'utilisateur connecté et le nom de la commune de
		// l'indicateur modifié
		Optional<Commune> nouvelleCommune = communeRepository.findByNomIgnoreCase(indicateurs.getCommune());
		if (verifierDoublonIndicateur(new CommuneIndicateurDto(indicateurs.getCommune(), true))) {

			if (nouvelleCommune.isPresent()) {
				indicateurAModifier.setCommune(nouvelleCommune.get());
				indicateurAModifier.setAlerte(indicateurs.getAlerte());
				repository.save(indicateurAModifier);
				return new CommuneIndicateurDto(indicateurAModifier.getCommune().getNom(),
						indicateurAModifier.getAlerte());
			} else {
				throw new CommuneInvalideException("Commune invalide");
			}
		} else {
			if (indicateurs.getCommune().equals(nomCommune)) {
				indicateurAModifier.setCommune(nouvelleCommune.get());
				indicateurAModifier.setAlerte(indicateurs.getAlerte());
				repository.save(indicateurAModifier);
				return new CommuneIndicateurDto(indicateurAModifier.getCommune().getNom(),
						indicateurAModifier.getAlerte());
			}
			throw new CommuneDejaSuivieException("Commune déjà présente dans la liste des indicateurs.");
		}

	}

	/**
	 * Cette méthode vérifie la présence de doublon lorsqu'on souhaite ajouter ou
	 * modifier un indicateur
	 * 
	 * @param commune Nom d'une commune
	 * @return renvoie true si la commune n'existe pas encore dans la liste des
	 *         indicateurs d'un utilisateur, sinon false
	 * @throws UtilisateurNonConnecteException renvoie une exception si aucun
	 *                                         utilisateur n'est connecté
	 */
	public Boolean verifierDoublonIndicateur(CommuneIndicateurDto commune) throws UtilisateurNonConnecteException {
		return recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getListeIndicateurs().stream()
				.filter(i -> i.getCommune().getNom().equals(commune.getCommune())).collect(Collectors.toList())
				.isEmpty();
	}

}
