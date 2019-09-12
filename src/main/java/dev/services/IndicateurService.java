package dev.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.controllers.dto.CommuneIndicateurDto;
import dev.controllers.dto.IndicateurDto;
import dev.controllers.dto.ModificationCommuneIndicateurDto;
import dev.entities.Commune;
import dev.entities.Indicateur;
import dev.entities.Utilisateur;
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
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(IndicateurService.class);

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
		log.info("Récupération de la lite des indicateurs...");
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
	 * @throws NombreIndicateursException      : Exception déclenchée si
	 *                                         l'utilisateur dispose déjà de 10
	 *                                         indicateurs
	 * @throws CommuneDejaSuivieException      : Exception déclenchée si
	 *                                         l'utilisateur tente de mettre deux
	 *                                         indicateurs sur une même commune
	 * @throws UtilisateurNonConnecteException
	 */
	public IndicateurDto sauvegarderNouvelIndicateur(CommuneIndicateurDto indicateur)
			throws NombreIndicateursException, CommuneDejaSuivieException, UtilisateurNonConnecteException {
		Utilisateur utilisateur;

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
			if (response.getUtilisateur().getListeIndicateurs().stream()
					.filter(i -> i.getCommune().getNom().equals(response.getCommune().getNom()))
					.collect(Collectors.toList()).isEmpty()) {
				repository.save(response);
			} else {
				throw new CommuneDejaSuivieException("Commune déjà présente dans la liste des indicateurs");
			}

			return new IndicateurDto(response.getUtilisateur().getEmail(), response.getCommune().getNom());
		} else {
			throw new NombreIndicateursException("Nombre d'indicateurs autorisés atteint");
		}

	}

	/**
	 * @param indicateur : Nom de la commune représentant l'indicateur à supprimer
	 * @return renvoie le mail tilisateur et le nom de la commune à supprimer
	 * @throws UtilisateurNonConnecteException
	 */
	public IndicateurDto supprimerUnIndicateur(CommuneIndicateurDto indicateur) throws UtilisateurNonConnecteException {

		List<Indicateur> indicateurs = repository
				.findByUtilisateurEmail(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail());
		Indicateur suppression = indicateurs.stream()
				.filter(i -> i.getCommune().getNom().equals(indicateur.getCommune())).collect(Collectors.toList())
				.get(0);
		repository.delete(suppression);
		return new IndicateurDto(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail(),
				suppression.getCommune().getNom());

	}

	/**
	 * @param nouvelIndicateur L'indicateur a modifier en base
	 * @return renvoie l'indicateur modifié
	 * @throws UtilisateurNonConnecteException
	 * @throws CommuneDejaSuivieException
	 */
	public IndicateurDto modifierIndicateur(ModificationCommuneIndicateurDto indicateurs)
			throws UtilisateurNonConnecteException, CommuneDejaSuivieException {

		// Recherche l'indicateur utilisateur à modifier
		Indicateur indicateurAModifier = repository
				.findByUtilisateurEmail(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail())
				.stream().filter(i -> i.getCommune().getNom().equals(indicateurs.getCommunes()[0]))
				.collect(Collectors.toList()).get(0);

		// Vérifie si le nouvel indicateur ne créérait pas de doublon dans la liste
		// existante de l'utilisateur. Si ok, on le modifie puis on retourne un objet
		// dto avec le mail de l'utilisateur connecté et le nom de la commune de
		// l'indicateur modifié

		if (verifierDoublonIndicateur(new CommuneIndicateurDto(indicateurs.getCommunes()[1]))) {
			Optional<Commune> nouvelleCommune = communeRepository.findByNomIgnoreCase(indicateurs.getCommunes()[1]);
			if (nouvelleCommune.isPresent()) {
				indicateurAModifier.setCommune(nouvelleCommune.get());
				repository.save(indicateurAModifier);
				return new IndicateurDto(recuperationUtilisateurConnecte.recupererUtilisateurViaEmail().getEmail(),
						indicateurAModifier.getCommune().getNom());
			} else {
				throw new CommuneInvalideException("Commune invalide");
			}
		} else {
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
