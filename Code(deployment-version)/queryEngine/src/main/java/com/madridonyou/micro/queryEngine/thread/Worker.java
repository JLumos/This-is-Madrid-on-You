package com.madridonyou.micro.queryEngine.thread;

import org.apache.jena.query.QuerySolution;

import com.madridonyou.micro.domain.inputs.RouteDefinition;
import com.madridonyou.micro.domain.rdf.Building;
import com.madridonyou.micro.queryEngine.osm.OsmQueryEngine;

public class Worker extends Thread {

	private QuerySolution row;
	private Building element;
	private RouteDefinition def;
	private String query;
	
	public String getQuery() {
		return query;
	}

	public QuerySolution getRow() {
		return row;
	}

	public void setRow(QuerySolution row) {
		this.row = row;
	}

	public Building getElement() {
		return element;
	}

	public void setElement(Building element) {
		this.element = element;
	}

	public Worker (QuerySolution row, RouteDefinition def) {
		this.row = row;
		this.def = def;
	}
	
	public void run() {
		long time_start, time_end;
		time_start = System.currentTimeMillis();

		this.element = new Building();
		OsmQueryEngine osm = new OsmQueryEngine();

		//Obtaining information from our RDF + DBpedia + EsDBpedia
		element.setName(row.getLiteral("name").getString());
		element.setAddress(row.getLiteral("address").getString());
		element.setDistrict(row.getLiteral("district") == null ? def.getDistrict() : row.getLiteral("district").getString());
		element.setLatitude(row.getLiteral("latitude").getDouble());
		element.setLongitude(row.getLiteral("longitude").getDouble());
		element.setPhoneNumber((row.getLiteral("phoneNumber") == null ? "No hay informacion" : row.getLiteral("phoneNumber").getString()));
		element.setAbstrac((row.getLiteral("abstract") == null ? "No hay informacion" : row.getLiteral("abstract").getString()));
		element.setImage(row.getResource("image") == null ? "/img/no-disponible.jpg" : row.getResource("image").asResource().getURI());
		element.setComment((row.getLiteral("comment") == null ? "No hay informacion" : row.getLiteral("comment").getString()));
		element.setHomepage((row.getResource("url") == null ? "No hay informacion" : row.getResource("url").getURI()));
		
		//Obtaining information from OSM
		osm.setLat("" + element.getLatitude());
		osm.setLon("" + element.getLongitude());
		this.query = osm.getDefaultQuery();
		element.setOsmresponse(osm.defaultFullQuery());
		time_end = System.currentTimeMillis();
		System.out.println("Time Overall for thread "+ this.getName() + ": " + ( time_end - time_start ) +" milliseconds");

	}

}
