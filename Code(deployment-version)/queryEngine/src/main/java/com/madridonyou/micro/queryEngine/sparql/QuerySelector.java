package com.madridonyou.micro.queryEngine.sparql;

import org.apache.commons.lang3.StringUtils;

public class QuerySelector {			

	public String getQueryForMonuments (String size, String distrito) {

		String district = distrito.equals("*") ? "?district" : "\"" + distrito + "\"";
		
		String query = "PREFIX dbp: <http://dbpedia.org/property/>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
				"PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\r\n" + 
				"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
				"PREFIX db: <http://dbpedia.org/>\r\n" + 
				"\r\n" + 
				"SELECT DISTINCT ?name ?address ?district ?latitude ?longitude ?phoneNumber ?image ?comment ?url ?abstract WHERE { \r\n" + 
				"  \r\n" + 
				"  ?a a dbo:HistoricBuilding .\r\n" + 
				"  ?a dbo:name ?name .\r\n" + 
				"  ?a dbo:address ?address .\r\n" + 
				"  ?a geo:lat ?latitude .\r\n" + 
				"  ?a geo:long ?longitude .\r\n" + 
				"  ?a dbp:phoneNumber ?phoneNumber .\r\n" + 
				"  ?a dbp:url ?url .\r\n" + 
				"  ?a rdfs:comment ?comment .\r\n" + 
				"  \r\n" + 
				"  OPTIONAL {\r\n" + 
				"    ?a owl:sameAs ?uri \r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://dbpedia.org/sparql> {\r\n" + 
				"        ?uri foaf:depiction ?image .\r\n" + 
				"        OPTIONAL {\r\n" + 
				"        	?uri dbo:abstract ?abstract .\r\n" + 
				"        	FILTER langMatches(lang(?abstract),'es')\r\n" + 
				"        }\r\n" + 
				"       }\r\n" + 
				"  	}\r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://es.dbpedia.org/sparql> {\r\n" + 
				"        ?uri foaf:depiction ?image \r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://es.dbpedia.org/sparql> {\r\n" + 
				"        ?uri dbo:wikiPageRedirects ?another .\r\n" + 
				"        ?another foaf:depiction ?image .\r\n" + 
				"      }\r\n" + 
				"  	}\r\n" + 
				"  }\r\n" + 
				"  \r\n" + 
				"  ?a dbo:district ?location .\r\n" + 
				"  ?location dbo:name " + district +" .\r\n" +
				"}\r\n" + 
				"\r\n" + 
				"ORDER BY RAND()\r\n" + 
				"LIMIT ";
		query += size != null && !size.equals("") && StringUtils.isNumeric(size) ? size : "5";
		return query;
	}

	public String getQueryForLibraries(String size, String distrito) {

		String district = distrito.equals("*") ? "?district" : "\"" + distrito + "\"";

		String query = "PREFIX dbp: <http://dbpedia.org/property/>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
				"PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\r\n" + 
				"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
				"PREFIX db: <http://dbpedia.org/>\r\n" + 
				"\r\n" + 
				"SELECT DISTINCT ?name ?address ?district ?latitude ?longitude ?phoneNumber ?image ?comment ?url ?abstract WHERE { \r\n" + 
				"  \r\n" + 
				"  ?a a dbo:Library .\r\n" + 
				"  ?a dbo:name ?name .\r\n" + 
				"  ?a dbo:address ?address .\r\n" + 
				"  ?a geo:lat ?latitude .\r\n" + 
				"  ?a geo:long ?longitude .\r\n" + 
				"  ?a dbp:phoneNumber ?phoneNumber .\r\n" + 
				"  ?a dbp:url ?url .\r\n" + 
				"  \r\n" + 
				"  OPTIONAL {\r\n" + 
				"    ?a owl:sameAs ?uri \r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://dbpedia.org/sparql> {\r\n" + 
				"        ?uri foaf:depiction ?image .\r\n" + 
				"        OPTIONAL {\r\n" + 
				"        	?uri dbo:abstract ?abstract .\r\n" + 
				"        	FILTER langMatches(lang(?abstract),'es')\r\n" + 
				"        }\r\n" + 
				"       }\r\n" + 
				"  	}\r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://es.dbpedia.org/sparql> {\r\n" + 
				"        ?uri foaf:depiction ?image \r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://es.dbpedia.org/sparql> {\r\n" + 
				"        ?uri dbo:wikiPageRedirects ?another .\r\n" + 
				"        ?another foaf:depiction ?image .\r\n" + 
				"      }\r\n" + 
				"  	}\r\n" + 
				"  }\r\n" + 
				"  \r\n" + 
				"  ?a dbo:district ?location .\r\n" + 
				"  ?location dbo:name " + district +" .\r\n" +
				"}\r\n" + 
				"\r\n" + 
				"ORDER BY RAND()\r\n" + 
				"LIMIT ";
		query += size != null && !size.equals("") && StringUtils.isNumeric(size) ? size : "5";
		return query;
	}

	public String getQueryForMuseums(String size, String distrito) {

		String district = distrito.equals("*") ? "?district" : "\"" + distrito + "\"";

		String query = "PREFIX dbp: <http://dbpedia.org/property/>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
				"PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\r\n" + 
				"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
				"PREFIX db: <http://dbpedia.org/>\r\n" + 
				"\r\n" + 
				"SELECT DISTINCT ?name ?address ?district ?latitude ?longitude ?phoneNumber ?image ?comment ?url ?abstract WHERE { \r\n" + 
				"  \r\n" + 
				"  ?a a dbo:Museum .\r\n" + 
				"  ?a dbo:name ?name .\r\n" + 
				"  ?a dbo:address ?address .\r\n" + 
				"  ?a geo:lat ?latitude .\r\n" + 
				"  ?a geo:long ?longitude .\r\n" + 
				"  ?a dbp:phoneNumber ?phoneNumber .\r\n" + 
				"  ?a dbp:url ?url .\r\n" + 
				"  ?a rdfs:comment ?comment .\r\n" + 
				"      \r\n" + 
				"  OPTIONAL {\r\n" + 
				"    ?a owl:sameAs ?uri \r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://dbpedia.org/sparql> {\r\n" + 
				"        ?uri foaf:depiction ?image .\r\n" + 
				"        OPTIONAL {\r\n" + 
				"        	?uri dbo:abstract ?abstract .\r\n" + 
				"        	FILTER langMatches(lang(?abstract),'es')\r\n" + 
				"        }\r\n" + 
				"       }\r\n" + 
				"  	}\r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://es.dbpedia.org/sparql> {\r\n" + 
				"        ?uri foaf:depiction ?image \r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://es.dbpedia.org/sparql> {\r\n" + 
				"        ?uri dbo:wikiPageRedirects ?another .\r\n" + 
				"        ?another foaf:depiction ?image .\r\n" + 
				"      }\r\n" + 
				"  	}\r\n" + 
				"  }\r\n" + 
				"  \r\n" + 
				"  ?a dbo:district ?location .\r\n" + 
				"  ?location dbo:name " + district +" .\r\n" +
				"}\r\n" + 
				"\r\n" + 
				"ORDER BY RAND()\r\n" + 
				"LIMIT ";
		query += size != null && !size.equals("") && StringUtils.isNumeric(size) ? size : "5";
		return query;
	}

	public String getQueryForReligious(String size, String distrito) {
		
		String district = distrito.equals("*") ? "?district" : "\"" + distrito + "\"";

		String query = "PREFIX dbp: <http://dbpedia.org/property/>\r\n" + 
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
				"PREFIX dbo: <http://dbpedia.org/ontology/>\r\n" + 
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\r\n" + 
				"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\r\n" + 
				"PREFIX db: <http://dbpedia.org/>\r\n" + 
				"\r\n" + 
				"SELECT DISTINCT ?name ?address ?district ?latitude ?longitude ?phoneNumber ?image ?comment ?url ?abstract WHERE { \r\n" + 
				"  \r\n" + 
				"  ?a a dbo:ReligiousBuilding .\r\n" + 
				"  ?a dbo:name ?name .\r\n" + 
				"  ?a dbo:address ?address .\r\n" + 
				"  ?a geo:lat ?latitude .\r\n" + 
				"  ?a geo:long ?longitude .\r\n" + 
				"  ?a dbp:phoneNumber ?phoneNumber .\r\n" + 
				"  ?a dbp:url ?url .\r\n" + 

				"  \r\n" + 
				"  OPTIONAL {\r\n" + 
				"    ?a owl:sameAs ?uri \r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://dbpedia.org/sparql> {\r\n" + 
				"        ?uri foaf:depiction ?image .\r\n" + 
				"        OPTIONAL {\r\n" + 
				"        	?uri dbo:abstract ?abstract .\r\n" + 
				"        	FILTER langMatches(lang(?abstract),'es')\r\n" + 
				"        }\r\n" + 
				"       }\r\n" + 
				"  	}\r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://es.dbpedia.org/sparql> {\r\n" + 
				"        ?uri foaf:depiction ?image \r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"    OPTIONAL {\r\n" + 
				"  		SERVICE <http://es.dbpedia.org/sparql> {\r\n" + 
				"        ?uri dbo:wikiPageRedirects ?another .\r\n" + 
				"        ?another foaf:depiction ?image .\r\n" + 
				"      }\r\n" + 
				"  	}\r\n" + 
				"  }\r\n" + 
				"  \r\n" + 
				"  ?a dbo:district ?location .\r\n" + 
				"  ?location dbo:name " + district +" .\r\n" +
				"}\r\n" + 
				"\r\n" + 
				"ORDER BY RAND()\r\n" + 
				"LIMIT ";
		query += size != null && !size.equals("") && StringUtils.isNumeric(size) ? size : "5";
		return query;
	}

	public String selectQuery (String categoria, String size, String distrito) {

		String query = "";
		
		if (categoria.equals("HistoricBuilding"))
			query = getQueryForMonuments(size, distrito); 
		else if (categoria.equals("Library"))
			query = getQueryForLibraries(size, distrito);
		else if (categoria.equals("Museum"))
			query = getQueryForMuseums(size, distrito);
		else 
			query = getQueryForReligious(size, distrito);
		
		System.out.println(query);
		return query;

	}
}
