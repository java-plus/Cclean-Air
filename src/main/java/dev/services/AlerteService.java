package dev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.controllers.dto.NiveauAlerteDto;
import dev.entities.Commune;
import dev.entities.Polluant;
import dev.repositories.ICommuneRepository;

/**
 * @author Guillaume
 *
 */
@Service
public class AlerteService {

	private ICommuneRepository repository;

	@Autowired
	public AlerteService(ICommuneRepository repository) {

		this.repository = repository;
	}

	/**
	 * @param commune Commune sur laquelle seront vérifiés les niveaux d'alerte
	 * @return valorise un objet de type NiveauAlerteDto si l'un des seuils est
	 *         franchi, sinon renvoi null
	 */
	public NiveauAlerteDto donnerNiveauAlerte(Commune commune) {
		List<Polluant> listePolluantsCommune = commune.getDonneesLocales().get(0).getQualiteAir().getListePolluants();

		List<Polluant> listeParticulesFines = listePolluantsCommune.stream()
				.filter(p -> p.getNom().equals("Particules fines")).collect(Collectors.toList());
		if (listeParticulesFines.stream().anyMatch(p -> p.getValeur() >= 80)) {
			return new NiveauAlerteDto("Particules fines", listeParticulesFines.get(0).getValeur());
		}

		List<Polluant> listeOzone = listePolluantsCommune.stream().filter(p -> p.getNom().equals("Ozone"))
				.collect(Collectors.toList());
		if (listeOzone.stream().anyMatch(p -> p.getValeur() >= 240)) {
			return new NiveauAlerteDto("Ozone", listeOzone.get(0).getValeur());
		}

		List<Polluant> listeDioxydeAzote = listePolluantsCommune.stream()
				.filter(p -> p.getNom().equals("Dioxyde d'azote")).collect(Collectors.toList());
		if (listeDioxydeAzote.stream().anyMatch(p -> p.getValeur() >= 200)) {
			return new NiveauAlerteDto("Dioxyde d'azote", listeDioxydeAzote.get(0).getValeur());
		}

		return null;
	}

}
