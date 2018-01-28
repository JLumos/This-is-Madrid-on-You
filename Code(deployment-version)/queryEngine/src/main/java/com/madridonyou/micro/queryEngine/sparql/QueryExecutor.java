package com.madridonyou.micro.queryEngine.sparql;

import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.Syntax;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import com.madridonyou.micro.domain.inputs.RouteDefinition;

public class QueryExecutor {

	public String getQuery() {
		return query;
	}

	private RouteDefinition def;
	private String query;
	
	public QueryExecutor (RouteDefinition def) {
		this.def = def;
	}
	
	public ResultSet executeQuery () {		
		
		this.query = new QuerySelector().selectQuery(def.getCategoria(), def.getSize(), def.getDistrict());
		Query query = QueryFactory.create(this.query);
		this.query = query.toString(Syntax.defaultQuerySyntax);
		Model model = this.openFile(def.getCategoria());
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		return qexec.execSelect();
	}
	
	private Model openFile (String categoria) {
		
		String filename;
		if (categoria.equals("HistoricBuilding"))
			filename = "MonumentosV2.ttl";
		else if (categoria.equals("Library"))
			filename = "BibliotecasV2.ttl";
		else if (categoria.equals("Museum"))
			filename = "MuseosV2.ttl";
		else if (categoria.equals("ReligiousBuilding"))
			filename = "Templos-catolicosV2.ttl";
		else
			filename = "Templos-no-catolicosV2.ttl";
		
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(filename);
		if (in == null) throw new IllegalArgumentException("File not found");
		model.read(in, null, "TTL");
		return model;
		
	}
}
