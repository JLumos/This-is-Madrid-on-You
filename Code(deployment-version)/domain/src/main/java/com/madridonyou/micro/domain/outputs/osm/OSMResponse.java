package com.madridonyou.micro.domain.outputs.osm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "version",
    "generator",
    "osm3s",
    "elements"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class OSMResponse {

    @JsonProperty("version")
    private Double version;
    @JsonProperty("generator")
    private String generator;
    @JsonProperty("osm3s")
    private Osm3s osm3s;
    @JsonProperty("elements")
    private List<Element> elements = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    private String amenity;
    
    public String getAmenity() {
		return amenity;
	}

	public void setAmenity(String amenity) {
		this.amenity = amenity;
	}

	@JsonProperty("version")
    public Double getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(Double version) {
        this.version = version;
    }

    @JsonProperty("generator")
    public String getGenerator() {
        return generator;
    }

    @JsonProperty("generator")
    public void setGenerator(String generator) {
        this.generator = generator;
    }

    @JsonProperty("osm3s")
    public Osm3s getOsm3s() {
        return osm3s;
    }

    @JsonProperty("osm3s")
    public void setOsm3s(Osm3s osm3s) {
        this.osm3s = osm3s;
    }

    @JsonProperty("elements")
    public List<Element> getElements() {
        return elements;
    }

    @JsonProperty("elements")
    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
