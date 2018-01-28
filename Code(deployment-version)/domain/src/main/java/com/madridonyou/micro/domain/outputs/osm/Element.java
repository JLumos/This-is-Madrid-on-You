
package com.madridonyou.micro.domain.outputs.osm;

import java.math.BigInteger;
import java.text.DecimalFormat;
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
	"id",
	"lat",
	"lon",
	"tags"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Element {

	@JsonProperty("id")
	private BigInteger id;
	@JsonProperty("lat")
	private Double lat;
	@JsonProperty("lon")
	private Double lon;
	@JsonProperty("tags")
	private Tags tags;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	private String representation = "";
	private String imagen;
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	private String ubicacion= "";
	private String tlf= "";
	private String web= "";
	private String facebook= "";
	private String horario= "";
	private String capacidad= "";
	private String descripcion= "";
	private String cocina= "";
	private Double distance;
	private String verboseDistance;


	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double latMonument, Double lonMonument, Double latElem, Double lonElem, String unit) {

		double theta = lonMonument - lonElem;
		double dist = Math.sin(deg2rad(latMonument)) * Math.sin(deg2rad(latElem)) + Math.cos(deg2rad(latMonument)) * Math.cos(deg2rad(latElem)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		this.distance = dist*1000;
		this.verboseDistance = "A " + new DecimalFormat("#.##").format(dist*1000) + " m aprox.";
	}

	public String getVerboseDistance() {
		return verboseDistance;
	}

	public void setVerboseDistance(String verboseDistance) {
		this.verboseDistance = verboseDistance;
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	@JsonProperty("id")
	public BigInteger getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(BigInteger id) {
		this.id = id;
	}

	@JsonProperty("lat")
	public Double getLat() {
		return lat;
	}

	@JsonProperty("lat")
	public void setLat(Double lat) {
		this.lat = lat;
	}

	@JsonProperty("lon")
	public Double getLon() {
		return lon;
	}

	@JsonProperty("lon")
	public void setLon(Double lon) {
		this.lon = lon;
	}

	@JsonProperty("tags")
	public Tags getTags() {
		return tags;
	}

	@JsonProperty("tags")
	public void setTags(Tags tags) {
		this.tags = tags;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}



	public String getRepresentation() {
		return representation;
	}

	public void setRepresentation(String representation) {
		this.representation = representation;
	}

	public void makeRepresentation () {

		String amenity = (String) this.tags.getAdditionalProperties().get("amenity");
		if (amenity != null && !amenity.equals("bicycle_parking"))
		{
			if (this.tags.getName() != null && this.tags.getAddrStreet() != null)
			{
				representation = "";

				ubicacion += this.tags.getAddrStreet() == null ? "" : this.tags.getAddrStreet();
				ubicacion += this.tags.getAddrHousenumber() == null ? "" : " Nº: " + this.tags.getAddrHousenumber() + "";

				tlf += this.tags.getContactPhone() == null ? "" : this.tags.getContactPhone() + "";
				tlf += this.tags.getPhone() == null ? "" : this.tags.getPhone() + "";

				web += this.tags.getContactWebsite() == null ? "" : this.tags.getContactWebsite() + "";
				web += this.tags.getWebsite() == null ? "" : this.tags.getWebsite() + "";

				facebook += this.tags.getContactFacebook() == null ? "" : this.tags.getContactFacebook() + "";

				horario += this.tags.getOpeningHours() == null ? "" : this.tags.getOpeningHours() + "";

				capacidad += this.tags.getCapacity() == null ? "" : this.tags.getCapacity() + " plazas" + "";

				descripcion += this.tags.getDescription() == null ? "" : this.tags.getDescription() + "";

				cocina += this.tags.getCuisine() == null ? "" : this.tags.getCuisine() + "";

			}
			else if (amenity != null && amenity.equals("taxi"))
			{
				representation = "";
				ubicacion += this.tags.getAddrStreet() == null ? "" : this.tags.getAddrStreet();
				ubicacion += this.tags.getAddrHousenumber() == null ? "" : " Nº: " + this.tags.getAddrHousenumber() + "";
				horario += this.tags.getOpeningHours() == null ? "" : this.tags.getOpeningHours() + "";
				capacidad += this.tags.getCapacity() == null ? "" : this.tags.getCapacity() + " plazas" + "";
				descripcion += this.tags.getDescription() == null ? "" : this.tags.getDescription() + "";
			}
			else this.representation = null;
		}

		else if (amenity != null && amenity.equals("bicycle_parking"))
		{
			representation = "";
			this.imagen = this.tags.getImage();
			capacidad += this.tags.getCapacity() == null ? "" : this.tags.getCapacity() + " plazas" + "";
		}
		else
		{
			//For Bus stops
			this.representation = "Nombre: " + this.tags.getName() + " (Parada nº " + this.tags.getRef() +")";
		}

	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCocina() {
		return cocina;
	}

	public void setCocina(String cocina) {
		this.cocina = cocina;
	}

}
