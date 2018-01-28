package com.madridonyou.micro.domain.rdf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.madridonyou.micro.domain.outputs.osm.Element;
import com.madridonyou.micro.domain.outputs.osm.OSMResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"name",
	"address",
	"district",
	"latitude",
	"longitude",
	"phoneNumber",
	"abstrac",
	"image",
	"comment",
	"osmNodes"
})
public class Building {

	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	@JsonProperty("district")
	private String district;
	@JsonProperty("latitude")
	private Double latitude;
	@JsonProperty("longitude")
	private Double longitude;
	@JsonProperty("phoneNumber")
	private String phoneNumber;
	@JsonProperty("abstrac")
	private String abstrac;
	@JsonProperty("image")
	private String image;
	@JsonProperty("comment")
	private String comment;
	private String homepage;
	@JsonProperty("osmNodes")
	
	private List<OSMResponse> osmNodes;

	private List<Element> pubAround;
	private List<Element> cafeAround;
	private List<Element> cinemaAround;
	private List<Element> theatreAround;
	private List<Element> barAround;
	@JsonProperty("restaurantes")
	private List<Element> restaurantAround;
	private List<Element> bicycleParkingAround;
	private List<Element> pharmacyAround;
	private List<Element> taxiAround;
	private List<Element> fastFoodAround;
	private List<Element> bankAround;
	private List<Element> parkingAround;
	private List<Element> busStopAround;

	
	public List<OSMResponse> getOsmNodes() {
		return osmNodes;
	}

	public void setOsmNodes(List<OSMResponse> osmNodes) {
		this.osmNodes = osmNodes;
	}

	public List<Element> getPubAround() {
		return pubAround;
	}

	public void setPubAround(List<Element> pubAround) {
		this.pubAround = pubAround;
	}

	public List<Element> getCafeAround() {
		return cafeAround;
	}

	public void setCafeAround(List<Element> cafeAround) {
		this.cafeAround = cafeAround;
	}

	public List<Element> getCinemaAround() {
		return cinemaAround;
	}

	public void setCinemaAround(List<Element> cinemaAround) {
		this.cinemaAround = cinemaAround;
	}

	public List<Element> getTheatreAround() {
		return theatreAround;
	}

	public void setTheatreAround(List<Element> theatreAround) {
		this.theatreAround = theatreAround;
	}

	public List<Element> getBarAround() {
		return barAround;
	}

	public void setBarAround(List<Element> barAround) {
		this.barAround = barAround;
	}
	
	@JsonProperty("restaurantes")
	public List<Element> getRestaurantAround() {
		return restaurantAround;
	}
	@JsonProperty("restaurantes")
	public void setRestaurantAround(List<Element> restaurantAround) {
		this.restaurantAround = restaurantAround;
	}

	public List<Element> getBicycleParkingAround() {
		return bicycleParkingAround;
	}

	public void setBicycleParkingAround(List<Element> bicycleParkingAround) {
		this.bicycleParkingAround = bicycleParkingAround;
	}

	public List<Element> getPharmacyAround() {
		return pharmacyAround;
	}

	public void setPharmacyAround(List<Element> pharmacyAround) {
		this.pharmacyAround = pharmacyAround;
	}

	public List<Element> getTaxiAround() {
		return taxiAround;
	}

	public void setTaxiAround(List<Element> taxiAround) {
		this.taxiAround = taxiAround;
	}

	public List<Element> getFastFoodAround() {
		return fastFoodAround;
	}

	public void setFastFoodAround(List<Element> fastFoodAround) {
		this.fastFoodAround = fastFoodAround;
	}

	public List<Element> getBankAround() {
		return bankAround;
	}

	public void setBankAround(List<Element> bankAround) {
		this.bankAround = bankAround;
	}

	public List<Element> getParkingAround() {
		return parkingAround;
	}

	public void setParkingAround(List<Element> parkingAround) {
		this.parkingAround = parkingAround;
	}

	public List<Element> getBusStopAround() {
		return busStopAround;
	}

	public void setBusStopAround(List<Element> busStopAround) {
		this.busStopAround = busStopAround;
	}

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("osmNodes")
	public List<OSMResponse> getOsmresponse() {
		return osmNodes;
	}

	@JsonProperty("osmNodes")
	public void setOsmresponse(List<OSMResponse> osmresponse) {
		this.osmNodes = osmresponse;
	}
	
	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("district")
	public String getDistrict() {
		return district;
	}

	@JsonProperty("district")
	public void setDistrict(String district) {
		this.district = district;
	}

	@JsonProperty("latitude")
	public Double getLatitude() {
		return latitude;
	}

	@JsonProperty("latitude")
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@JsonProperty("longitude")
	public Double getLongitude() {
		return longitude;
	}

	@JsonProperty("longitude")
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@JsonProperty("phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("abstrac")
	public String getAbstrac() {
		return abstrac;
	}

	@JsonProperty("abstrac")
	public void setAbstrac(String abstrac) {
		this.abstrac = abstrac;
	}

	@JsonProperty("image")
	public String getImage() {
		return image;
	}

	@JsonProperty("image")
	public void setImage(String image) {
		this.image = image;
	}

	@JsonProperty("comment")
	public String getComment() {
		return comment;
	}

	@JsonProperty("comment")
	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonProperty("homepage")
	public String getHomepage() {
		return homepage;
	}

	@JsonProperty("homepage")
	public void setHomepage(String homepage) {
		this.homepage = homepage;
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