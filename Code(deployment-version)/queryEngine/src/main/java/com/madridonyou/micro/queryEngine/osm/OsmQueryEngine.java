package com.madridonyou.micro.queryEngine.osm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.madridonyou.micro.domain.outputs.osm.OSMResponse;

@Component
public class OsmQueryEngine {

	private String key;
	private String value;
	private String radius = "500";
	private String lat;
	private String lon;
	private String limit = "4";
	private RestTemplate template = new RestTemplate();
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}

	
	public OsmQueryEngine(String key, String value, String radius, String lat, String lon, String limit) {
		super();
		this.key = key;
		this.value = value;
		this.radius = radius;
		this.lat = lat;
		this.lon = lon;
		this.limit = limit;
	}
	
	public OsmQueryEngine () {}
	
	public String queryForSomething() {

		return "<osm-script output=\"json\">\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"" + this.key + "\" v=\"" + this.value + "\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"" + this.limit + "\"/>\r\n" + 
				"</osm-script>";
	}
	
	public String queryForFastFood () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"fast_food\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"8\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForPub () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"pub\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForCinema () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"cinema\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForTheatre () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"theatre\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForBar () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bar\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForRestaurant () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"restaurant\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"6\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForBicycleParking () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bicycle_parking\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForPharmacy () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"pharmacy\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForTaxi () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"taxi\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForBank () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bank\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"10\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForParking () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"parking\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"12\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public String queryForBusStop () {
		return "<osm-script output=\"json\">\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"" + this.radius +"\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"highway\" v=\"bus_stop\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"</osm-script>";
	}
	
	public List<OSMResponse> defaultFullQuery () {
		
		List<OSMResponse> list = new LinkedList<OSMResponse>();
		
		String query = "<osm-script output=\"json\">\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"150\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"pub\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"150\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"cafe\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"cinema\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"theatre\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"150\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bar\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"250\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"restaurant\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"6\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bicycle_parking\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"pharmacy\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"taxi\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"100\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"fast_food\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"7\"/>\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bank\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"10\"/>  \r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"parking\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"12\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"200\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"highway\" v=\"bus_stop\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"8\"/>  \r\n" + 
				"  \r\n" + 
				"</osm-script>";
		OSMResponse response = this.executeQuery(query);
		list.add(response);

		return list;
	}
	
	public OSMResponse executeQuery (String query) {
		
		OSMResponse response = null;
		long time_start, time_end;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application","xml"));

		HttpEntity<String> entity = new HttpEntity<String>(query ,headers);
		
		time_start = System.currentTimeMillis();
		try {
			response = this.template.postForObject(
					new URI("http://overpass-api.de/api/interpreter"), 
					entity, 
					OSMResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			response = null;
			System.out.println("TOO MANY REQUESTS: " + e.getMessage());
		}
		
		time_end = System.currentTimeMillis();
		System.out.println("Time Overall for Download "+ this.lat + ": " + ( time_end - time_start ) +" milliseconds");

		return response;
	}

	public String getDefaultQuery ()  {
		return "<p> \" <osm-script output=\"json\">\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"150\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"pub\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"150\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"cafe\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"cinema\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"theatre\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"150\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bar\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"250\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"restaurant\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"6\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bicycle_parking\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"pharmacy\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"taxi\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"5\"/>\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"100\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"fast_food\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"7\"/>\r\n" + 
				"  \r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"bank\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"10\"/>  \r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"500\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"amenity\" v=\"parking\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"12\"/>\r\n" + 
				"\r\n" + 
				"<query type=\"node\">\r\n" + 
				"  <around radius=\"200\" lat=\"" + this.lat + "\" lon=\"" + this.lon + "\"/>\r\n" + 
				"  <has-kv k=\"highway\" v=\"bus_stop\"/>\r\n" + 
				"</query>\r\n" + 
				"<print limit=\"8\"/>  \r\n" + 
				"  \r\n" + 
				"</osm-script> \" </p>";
	}

}
