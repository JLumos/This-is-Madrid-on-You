
package com.madridonyou.micro.domain.outputs.osm;

import java.util.HashMap;
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
    "addr:street",
    "addr:housenumber",
    "contact:phone",
    "contact:website",
    "contact:facebook",
    "phone",
    "opening_hours",
    "website",
    "name",
    "capacity",
    "image",
    "description",
    "cuisine",
    "atm",
    "ref"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tags {

    @JsonProperty("addr:street")
    private String addrStreet;
    @JsonProperty("addr:housenumber")
    private String addrHousenumber;
    @JsonProperty("contact:phone")
    private String contactPhone;
    @JsonProperty("contact:website")
    private String contactWebsite;
    @JsonProperty("contact:facebook")
    private String contactFacebook;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("opening_hours")
    private String openingHours;
    @JsonProperty("website")
    private String website;
    @JsonProperty("name")
    private String name;
    @JsonProperty("capacity")
    private String capacity;
    @JsonProperty("image")
    private String image;
    @JsonProperty("description")
    private String description;
    @JsonProperty("cuisine")
    private String cuisine;
    @JsonProperty("atm")
    private String atm;
    @JsonProperty("ref")
    private String ref;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("addr:street")
    public String getAddrStreet() {
        return addrStreet;
    }

    @JsonProperty("addr:street")
    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    @JsonProperty("addr:housenumber")
    public String getAddrHousenumber() {
        return addrHousenumber;
    }

    @JsonProperty("addr:housenumber")
    public void setAddrHousenumber(String addrHousenumber) {
        this.addrHousenumber = addrHousenumber;
    }

    @JsonProperty("contact:phone")
    public String getContactPhone() {
        return contactPhone;
    }

    @JsonProperty("contact:phone")
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @JsonProperty("contact:website")
    public String getContactWebsite() {
        return contactWebsite;
    }

    @JsonProperty("contact:website")
    public void setContactWebsite(String contactWebsite) {
        this.contactWebsite = contactWebsite;
    }

    @JsonProperty("contact:facebook")
    public String getContactFacebook() {
        return contactFacebook;
    }

    @JsonProperty("contact:facebook")
    public void setContactFacebook(String contactFacebook) {
        this.contactFacebook = contactFacebook;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("opening_hours")
    public String getOpeningHours() {
        return openingHours;
    }

    @JsonProperty("opening_hours")
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("capacity")
    public String getCapacity() {
        return capacity;
    }

    @JsonProperty("capacity")
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("cuisine")
    public String getCuisine() {
        return cuisine;
    }

    @JsonProperty("cuisine")
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @JsonProperty("atm")
    public String getAtm() {
        return atm;
    }

    @JsonProperty("atm")
    public void setAtm(String atm) {
        this.atm = atm;
    }

    @JsonProperty("ref")
    public String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(String ref) {
        this.ref = ref;
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
