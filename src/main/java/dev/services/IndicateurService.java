package dev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.controllers.dto.CommuneIndicateurDto;
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

}
