package dev.controllers.dto;

import dev.controllers.dto.visualiserDonnees.CommuneDtoVisualisation;
import dev.controllers.dto.visualiserDonnees.PolluantDtoVisualisation;

import java.time.ZonedDateTime;

/**
 * @author Cécile Objet Dto qui va servir à l'affichage de l'historique il sera
 *         retourné sous forme de liste pour afficher toute la période pour un
 *         polluant donné.
 */
public class DonneesLocalesHistorique {

	/**
	 * objet Dto polluantDtoVisualisation qui reprend les informations
	 * indispensables pour l'affichage : le nom, la valeur et l'unité
	 */
	private PolluantDtoVisualisation polluantDtoVisualisation;

	/**
	 * objet dto CommuneDtoVisualisation qui reprend les informations indispensables
	 * pour l'affichage : le nom de la commune et le nombre d'habitants
	 */
	private CommuneDtoVisualisation communeDtoVisualisation;

	/**
	 * Date des valeurs à afficher
	 */
	private ZonedDateTime date;

	public DonneesLocalesHistorique() {
	}

	public DonneesLocalesHistorique(PolluantDtoVisualisation polluantDtoVisualisation,
			CommuneDtoVisualisation communeDtoVisualisation, ZonedDateTime date) {
		this.polluantDtoVisualisation = polluantDtoVisualisation;
		this.communeDtoVisualisation = communeDtoVisualisation;
		this.date = date;
	}

	public PolluantDtoVisualisation getPolluantDtoVisualisation() {
		return polluantDtoVisualisation;
	}

	public void setPolluantDtoVisualisation(PolluantDtoVisualisation polluantDtoVisualisation) {
		this.polluantDtoVisualisation = polluantDtoVisualisation;
	}

	public CommuneDtoVisualisation getCommuneDtoVisualisation() {
		return communeDtoVisualisation;
	}

	public void setCommuneDtoVisualisation(CommuneDtoVisualisation communeDtoVisualisation) {
		this.communeDtoVisualisation = communeDtoVisualisation;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
}
