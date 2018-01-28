package com.madridonyou.micro.domain.trace;

import com.madridonyou.micro.domain.inputs.RouteDefinition;

public class TraceGenerator {

	public String getTrace() {
		return trace;
	}

	public TraceGenerator() {

		this.trace = "<h2> Gracias por utilizar Madrid On You! </h2> <br> Si estas estudiando algo relacionado con los datos enlazados \n"
				+ "o la web semántica, la siguiente información puede serte de mucha ayuda.<br> Esperamos que te sea util! \n \n \n";
	}

	private String trace;

	public void commitQueryStart () {

		this.trace += "<h4> Paso 1 - Obtener información de nuestros RDF, enlazados con DBpedia y EsDBpedia<h4>";
		this.trace += "<p>En primer lugar, Madrid on You realiza una consulta SPARQL a los ficheros RDF que conforman su base de conocimiento. \n"
				+ "En el caso de la ruta que se ha generado para ti, esta es la consulta que hemos realizado: </p>\n";
	}

	public void setQueryRdf (String query, RouteDefinition def) {
		
		String district = def.getDistrict().equals("*") ? "?district" : "\"" + def.getDistrict() + "\"";

		this.trace += "<strong> " + 
				"<p>   SELECT DISTINCT ?name ?address ?district ?latitude ?longitude ?phoneNumber ?image ?comment ?url ?abstract WHERE { \r\n</p>" + 
				"<p>   \r\n</p>" + 
				"<p>   ?a a dbo:" + def.getCategoria() + ".\r\n</p>" + 
				"<p>   ?a dbo:name ?name .\r\n</p>" + 
				"<p>   ?a dbo:address ?address .\r\n</p>" + 
				"<p>   ?a geo:lat ?latitude .\r\n</p>" + 
				"<p>   ?a geo:long ?longitude .\r\n</p>" + 
				"<p>   ?a dbp:phoneNumber ?phoneNumber .\r\n</p>" + 
				"<p>   ?a dbp:url ?url .\r\n</p>" + 
				"<p>   \r\n</p>" + 
				"<p>   OPTIONAL {\r\n</p>" + 
				"<p>     ?a owl:sameAs ?uri \r\n</p>" + 
				"<p>     OPTIONAL {\r\n</p>" + 
				"<p>   		SERVICE <http://dbpedia.org/sparql> {\r\n</p>" + 
				"<p>         ?uri foaf:depiction ?image .\r\n</p>" + 
				"<p>         OPTIONAL {\r\n</p>" + 
				"<p>         	?uri dbo:abstract ?abstract .\r\n</p>" + 
				"<p>         	FILTER langMatches(lang(?abstract),'es')\r\n</p>" + 
				"<p>         }\r\n</p>" + 
				"<p>        }\r\n</p>" + 
				"<p>   	}\r\n</p>" + 
				"<p>     OPTIONAL {\r\n</p>" + 
				"<p>   		SERVICE <http://es.dbpedia.org/sparql> {\r\n</p>" + 
				"<p>         ?uri foaf:depiction ?image \r\n</p>" + 
				"<p>       }\r\n</p>" + 
				"<p>     }\r\n</p>" + 
				"<p>     OPTIONAL {\r\n</p>" + 
				"<p>   		SERVICE <http://es.dbpedia.org/sparql> {\r\n</p>" + 
				"<p>         ?uri dbo:wikiPageRedirects ?another .\r\n</p>" + 
				"<p>         ?another foaf:depiction ?image .\r\n</p>" + 
				"<p>       }\r\n</p>" + 
				"<p>   	}\r\n</p>" + 
				"<p>   }\r\n</p>" + 
				"<p>   \r\n</p>" + 
				"<p>   ?a dbo:district ?location .\r\n</p>" + 
				"<p>   ?location dbo:name </p>" + district + " .\r\n</p>" +
				"<p> }\r\n</p>" + 
				"<p> \r\n</p>" + 
				"<p> ORDER BY RAND()\r\n</p>" + 
				"<p> LIMIT " + def.getSize() + "</p>" +
				" </strong> "+ " \n \n";
	}

	public void commitQueryEnd () {
		this.trace += "<p>La query se ha realizado en base a lo que has seleccionado en la ventana de generación de ruta. La categoría de los \n"
				+ "edificios, el distrito y el número de elementos que contiene la ruta se obtienen de ahí. Además, hemos realizado varias \n"
				+ "consultas a DBPedia y EsDBpedia mediante las queries federadas que ves, para obtener la imagen y la descripción de los elementos \n"
				+ "En algunos casos hemos conseguido obtener esa información y, en otros, no.</p>";

		this.trace += "<h4> Paso 2 - Obtener información de Open Street Map mediante la API Overpass<h4>";
		this.trace += "<p> Hemos obtenido información de Open Street Map con consultas muy similares a esta, donde solo cambian las"
				+ " coordenadas de latitud y longitud:</p>";
		this.trace += "<strong> node\r\n" + 
				"  (around:150,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"pub\"];\r\n" + 
				"out 5;\r\n" + 
				"<br> node\r\n" + 
				"  (around:500,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"cafe\"];\r\n" + 
				"out 10;\r\n" + 
				"<br> node\r\n" + 
				"  (around:500,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"cinema\"];\r\n" + 
				"out 5;\r\n" + 
				"<br> node\r\n" + 
				"  (around:500,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"theatre\"];\r\n" + 
				"out 5;\r\n" + 
				"<br> node\r\n" + 
				"  (around:150,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"bar\"];\r\n" + 
				"out 5;\r\n" + 
				"<br> node\r\n" + 
				"  (around:250,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"restaurant\"];\r\n" + 
				"out 6;\r\n" + 
				"<br> node\r\n" + 
				"  (around:500,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"bicycle_parking\"];\r\n" + 
				"out 5;\r\n" + 
				"<br> node\r\n" + 
				"  (around:500,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"pharmacy\"];\r\n" + 
				"out 5;\r\n" + 
				"<br> node\r\n" + 
				"  (around:500,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"taxi\"];\r\n" + 
				"out 5;\r\n" + 
				"<br> node\r\n" + 
				"  (around:100,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"fast_food\"];\r\n" + 
				"out 7;\r\n" + 
				"<br> node\r\n" + 
				"  (around:500,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"bank\"];\r\n" + 
				"out 10;\r\n" + 
				"<br> node\r\n" + 
				"  (around:500,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"amenity\"=\"parking\"];\r\n" + 
				"out 12;\r\n" + 
				"<br> node\r\n" + 
				"  (around:200,40.418834686279,-3.6948211193085)\r\n" + 
				"  [\"highway\"=\"bus_stop\"];\r\n" + 
				"out 8;</strong>";

	}

	public void commitOSMEnd () {
		this.trace += "<p>Como puedes ver, en cada una de las queries se especifica con el parámetro around un radio de X metros alrededor de unas coordenadas concretas y se buscan nodos de un tipo concreto (pub, cafe, bares...). El parámetro out indica el número máximo de nodos de ese tipo que se buscarán.</p>"
				+ "\n \n <p>Esperamos que esto te haya sido de ayuda para comprender cómo funciona internamente la aplicación, y que hayas aprendido algo ;)<p> \n"
				+ "<h2>El equipo de Madrid on You.</h2>";

	}

}
