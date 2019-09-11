package dev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.controllers.dto.CommuneIndicateurDto;
import dev.entities.Indicateur;
import dev.exceptions.NombreIndicateursException;
import dev.repositories.IIndicateurRepository;

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

	@Autowired
	public IndicateurService(IIndicateurRepository repository) {

		this.repository = repository;
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
	public Indicateur sauvegarderNouvelIndicateur(Indicateur indicateur) throws NombreIndicateursException {
		if (indicateur.getUtilisateur().getListeIndicateurs().size() < 11) {
			repository.save(indicateur);
			return indicateur;
		} else {
			throw new NombreIndicateursException("Nombre d'indicateurs autorisés atteint");
		}

	}

}
