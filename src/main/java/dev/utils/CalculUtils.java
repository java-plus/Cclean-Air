package dev.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.controllers.dto.recherche.CommuneCarteDto;
import dev.services.CommuneService;

/**
 * Classe utilitaire regroupant des méthodes de calcul.
 */
@Component
public class CalculUtils {

	private CommuneService service;

	@Autowired
	public CalculUtils(CommuneService service) {
		super();
		this.service = service;
	}

	/**
	 * Méthode permettant de calculer la distance séparant deux éléments sur un axe
	 * x/y.
	 * 
	 * @param x1 : [double] valeur de coordonnée sur l'axe x de l'élément 1
	 *           (longitude)
	 * @param y1 : [double] valeur de coordonnée sur l'axe y de l'élément 1
	 *           (latitude)
	 * @param x2 : [double] valeur de coordonnée sur l'axe x de l'élément 2
	 * @param y2 : [double] valeur de coordonnée sur l'axe y de l'élément 2
	 * @return : [double] valeur de distance entre les deux éléments
	 */
	public double calculerDistanceEntreDeuxPoints(double x1, double y1, double x2, double y2) {

		double ac = Math.abs(y2 - y1);
		double cb = Math.abs(x2 - x1);

		return Math.hypot(ac, cb);
	}

	/**
	 * @param coordonnees Tableau de coordonnées fournies lors de l'appel à la
	 *                    fonction
	 * @return Retourne un dto de la commune la plus proche des coordonnées
	 *         renseignées
	 */
	public CommuneCarteDto calculerCommuneLaPluProche(Double[] coordonnees) {

		List<CommuneCarteDto> listeCommunes = service.recupererDonnesCarte();
		Double distance = 0.0;
		CommuneCarteDto commune = null;
		for (int i = 0; i < listeCommunes.size(); i++) {

			Double calcul = calculerDistanceEntreDeuxPoints(listeCommunes.get(i).getLongitude(),
					listeCommunes.get(i).getLatitude(), coordonnees[0], coordonnees[1]);
			if (calcul > distance) {
				commune = listeCommunes.get(i);
			}
		}
		return new CommuneCarteDto(commune.getCodeINSEE(), commune.getCodePostal(), commune.getNomCommune(),
				commune.getLatitude(), commune.getLongitude(), null);
	}

}
