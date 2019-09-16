
package dev.controllers.dto.qualiteAir;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "polluant",
    "intervalle",
    "download_url",
    "next",
    "prev",
    "moyenne",
    "mesures"
})
public class QualiteAirDtoApi {

    @JsonProperty("polluant")
    private Polluant polluant;
    @JsonProperty("intervalle")
    private String intervalle;
    @JsonProperty("download_url")
    private String downloadUrl;
    @JsonProperty("next")
    private String next;
    @JsonProperty("prev")
    private String prev;
    @JsonProperty("moyenne")
    private String moyenne;
    @JsonProperty("mesures")
    private List<Mesure> mesures = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public QualiteAirDtoApi() {
    }

    /**
     * 
     * @param mesures
     * @param intervalle
     * @param polluant
     * @param next
     * @param moyenne
     * @param downloadUrl
     * @param prev
     */
    public QualiteAirDtoApi(Polluant polluant, String intervalle, String downloadUrl, String next, String prev, String moyenne, List<Mesure> mesures) {
        super();
        this.polluant = polluant;
        this.intervalle = intervalle;
        this.downloadUrl = downloadUrl;
        this.next = next;
        this.prev = prev;
        this.moyenne = moyenne;
        this.mesures = mesures;
    }

    @JsonProperty("polluant")
    public Polluant getPolluant() {
        return polluant;
    }

    @JsonProperty("polluant")
    public void setPolluant(Polluant polluant) {
        this.polluant = polluant;
    }

    @JsonProperty("intervalle")
    public String getIntervalle() {
        return intervalle;
    }

    @JsonProperty("intervalle")
    public void setIntervalle(String intervalle) {
        this.intervalle = intervalle;
    }

    @JsonProperty("download_url")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    @JsonProperty("download_url")
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @JsonProperty("next")
    public String getNext() {
        return next;
    }

    @JsonProperty("next")
    public void setNext(String next) {
        this.next = next;
    }

    @JsonProperty("prev")
    public String getPrev() {
        return prev;
    }

    @JsonProperty("prev")
    public void setPrev(String prev) {
        this.prev = prev;
    }

    @JsonProperty("moyenne")
    public String getMoyenne() {
        return moyenne;
    }

    @JsonProperty("moyenne")
    public void setMoyenne(String moyenne) {
        this.moyenne = moyenne;
    }

    @JsonProperty("mesures")
    public List<Mesure> getMesures() {
        return mesures;
    }

    @JsonProperty("mesures")
    public void setMesures(List<Mesure> mesures) {
        this.mesures = mesures;
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
        return new ToStringBuilder(this).append("polluant", polluant).append("intervalle", intervalle).append("downloadUrl", downloadUrl).append("next", next).append("prev", prev).append("moyenne", moyenne).append("mesures", mesures).append("additionalProperties", additionalProperties).toString();
    }

}
