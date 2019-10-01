
package dev.controllers.dto.qualiteAir;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "nom_departement", "nom_zone", "nom_station", "code_station", "polluant", "niveau", "unite",
		"mesure", "etat", "date", "TZ" })
public class Datum {

	@JsonProperty("nom_departement")
	private String nomDepartement;
	@JsonProperty("nom_zone")
	private String nomZone;
	@JsonProperty("nom_station")
	private String nomStation;
	@JsonProperty("code_station")
	private String codeStation;
	@JsonProperty("polluant")
	private String polluant;
	@JsonProperty("niveau")
	private Double niveau;
	@JsonProperty("unite")
	private String unite;
	@JsonProperty("mesure")
	private String mesure;
	@JsonProperty("etat")
	private String etat;
	@JsonProperty("date")
	private String date;
	@JsonProperty("TZ")
	private String tZ;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Datum() {
	}

	/**
	 * 
	 * @param etat
	 * @param unite
	 * @param codeStation
	 * @param niveau
	 * @param polluant
	 * @param nomStation
	 * @param tZ
	 * @param date
	 * @param mesure
	 * @param nomZone
	 * @param nomDepartement
	 */
	public Datum(String nomDepartement, String nomZone, String nomStation, String codeStation, String polluant,
			Double niveau, String unite, String mesure, String etat, String date, String tZ) {
		super();
		this.nomDepartement = nomDepartement;
		this.nomZone = nomZone;
		this.nomStation = nomStation;
		this.codeStation = codeStation;
		this.polluant = polluant;
		this.niveau = niveau;
		this.unite = unite;
		this.mesure = mesure;
		this.etat = etat;
		this.date = date;
		this.tZ = tZ;
	}

	@JsonProperty("nom_departement")
	public String getNomDepartement() {
		return nomDepartement;
	}

	@JsonProperty("nom_departement")
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}

	@JsonProperty("nom_zone")
	public String getNomZone() {
		return nomZone;
	}

	@JsonProperty("nom_zone")
	public void setNomZone(String nomZone) {
		this.nomZone = nomZone;
	}

	@JsonProperty("nom_station")
	public String getNomStation() {
		return nomStation;
	}

	@JsonProperty("nom_station")
	public void setNomStation(String nomStation) {
		this.nomStation = nomStation;
	}

	@JsonProperty("code_station")
	public String getCodeStation() {
		return codeStation;
	}

	@JsonProperty("code_station")
	public void setCodeStation(String codeStation) {
		this.codeStation = codeStation;
	}

	@JsonProperty("polluant")
	public String getPolluant() {
		return polluant;
	}

	@JsonProperty("polluant")
	public void setPolluant(String polluant) {
		this.polluant = polluant;
	}

	@JsonProperty("niveau")
	public Double getNiveau() {
		return niveau;
	}

	@JsonProperty("niveau")
	public void setNiveau(Double niveau) {
		this.niveau = niveau;
	}

	@JsonProperty("unite")
	public String getUnite() {
		return unite;
	}

	@JsonProperty("unite")
	public void setUnite(String unite) {
		this.unite = unite;
	}

	@JsonProperty("mesure")
	public String getMesure() {
		return mesure;
	}

	@JsonProperty("mesure")
	public void setMesure(String mesure) {
		this.mesure = mesure;
	}

	@JsonProperty("etat")
	public String getEtat() {
		return etat;
	}

	@JsonProperty("etat")
	public void setEtat(String etat) {
		this.etat = etat;
	}

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
	}

	@JsonProperty("TZ")
	public String getTZ() {
		return tZ;
	}

	@JsonProperty("TZ")
	public void setTZ(String tZ) {
		this.tZ = tZ;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("nomDepartement", nomDepartement).append("nomZone", nomZone)
				.append("nomStation", nomStation).append("codeStation", codeStation).append("polluant", polluant)
				.append("niveau", niveau).append("unite", unite).append("mesure", mesure).append("etat", etat)
				.append("date", date).append("tZ", tZ).append("additionalProperties", additionalProperties).toString();
	}

}
