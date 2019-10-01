
package dev.controllers.dto.qualiteAir;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "nom", "code", "unite", "seuils" })
public class Polluant {

	@JsonProperty("id")
	private String id;
	@JsonProperty("nom")
	private String nom;
	@JsonProperty("code")
	private String code;
	@JsonProperty("unite")
	private String unite;
	@JsonProperty("seuils")
	private List<Object> seuils = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Polluant() {
	}

	/**
	 * 
	 * @param unite
	 * @param id
	 * @param seuils
	 * @param code
	 * @param nom
	 */
	public Polluant(String id, String nom, String code, String unite, List<Object> seuils) {
		super();
		this.id = id;
		this.nom = nom;
		this.code = code;
		this.unite = unite;
		this.seuils = seuils;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("nom")
	public String getNom() {
		return nom;
	}

	@JsonProperty("nom")
	public void setNom(String nom) {
		this.nom = nom;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("unite")
	public String getUnite() {
		return unite;
	}

	@JsonProperty("unite")
	public void setUnite(String unite) {
		this.unite = unite;
	}

	@JsonProperty("seuils")
	public List<Object> getSeuils() {
		return seuils;
	}

	@JsonProperty("seuils")
	public void setSeuils(List<Object> seuils) {
		this.seuils = seuils;
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
		return new ToStringBuilder(this).append("id", id).append("nom", nom).append("code", code).append("unite", unite)
				.append("seuils", seuils).append("additionalProperties", additionalProperties).toString();
	}

}
