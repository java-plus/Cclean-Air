package dev.controllers.dto;

/**
 * Objet DTO pour récupérer le nom de la commune et le code insee d'un
 * indicateur dans le but d'afficher ses données
 * 
 * @author Cécile
 *
 */
public class IndicateurAffichageDto {

	private String nomCommune;
	private String codeInsee;
	private Boolean alerte;

	/**
	 * Constructeur
	 * 
	 */
	public IndicateurAffichageDto() {

	}

	/**
	 * Constructeur
	 * 
	 * @param nomCommune
	 * @param codeInsee
	 */
	public IndicateurAffichageDto(String nomCommune, String codeInsee, Boolean alerte) {
		super();
		this.nomCommune = nomCommune;
		this.codeInsee = codeInsee;
		this.alerte = alerte;
	}

	/**
	 * Getter
	 * 
	 * @return the nomCommune
	 */
	public String getNomCommune() {
		return nomCommune;
	}

	/**
	 * Setter
	 * 
	 * @param nomCommune the nomCommune to set
	 */
	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	/**
	 * Getter
	 * 
	 * @return the codeInsee
	 */
	public String getCodeInsee() {
		return codeInsee;
	}

	/**
	 * Setter
	 * 
	 * @param codeInsee the codeInsee to set
	 */
	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	/**
	 * Getter
	 * 
	 * @return the alerte
	 */
	public Boolean getAlerte() {
		return alerte;
	}

	/**
	 * Setter
	 * 
	 * @param alerte the alerte to set
	 */
	public void setAlerte(Boolean alerte) {
		this.alerte = alerte;
	}

}
