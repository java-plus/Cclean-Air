
package dev.controllers.dto.qualiteAir;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "departement", "zone", "name", "code", "data" })
public class Mesure {

	@JsonProperty("departement")
	private String departement;
	@JsonProperty("zone")
	private String zone;
	@JsonProperty("name")
	private String name;
	@JsonProperty("code")
	private String code;
	@JsonProperty("data")
	private List<Datum> data = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Mesure() {
	}

	/**
	 * 
	 * @param name
	 * @param data
	 * @param code
	 * @param zone
	 * @param departement
	 */
	public Mesure(String departement, String zone, String name, String code, List<Datum> data) {
		super();
		this.departement = departement;
		this.zone = zone;
		this.name = name;
		this.code = code;
		this.data = data;
	}

	@JsonProperty("departement")
	public String getDepartement() {
		return departement;
	}

	@JsonProperty("departement")
	public void setDepartement(String departement) {
		this.departement = departement;
	}

	@JsonProperty("zone")
	public String getZone() {
		return zone;
	}

	@JsonProperty("zone")
	public void setZone(String zone) {
		this.zone = zone;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("data")
	public List<Datum> getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(List<Datum> data) {
		this.data = data;
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
		return new ToStringBuilder(this).append("departement", departement).append("zone", zone).append("name", name)
				.append("code", code).append("data", data).append("additionalProperties", additionalProperties)
				.toString();
	}

}
