package dev.utils;

import org.springframework.stereotype.Component;

/**
 * Classe utilitaire regroupant des méthodes de calcul.
 */
@Component
public class CalculUtils {

	/**
	 * Méthode permettant de calculer la distance séparant deux éléments sur un axe
	 * x/y.
	 * 
	 * @param x1 : [double] valeur de coordonnée sur l'axe x de l'élément 1
	 * @param y1 : [double] valeur de coordonnée sur l'axe y de l'élément 1
	 * @param x2 : [double] valeur de coordonnée sur l'axe x de l'élément 2
	 * @param y2 : [double] valeur de coordonnée sur l'axe y de l'élément 2
	 * @return : [double] valeur de distance entre les deux éléments
	 */
	public double calculerDistanceEntreDeuxPoints(double x1, double y1, double x2, double y2) {

		double ac = Math.abs(y2 - y1);
		double cb = Math.abs(x2 - x1);

		return Math.hypot(ac, cb);
	}
}
