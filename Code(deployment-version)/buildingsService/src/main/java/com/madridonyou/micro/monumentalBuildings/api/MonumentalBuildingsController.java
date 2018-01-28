package com.madridonyou.micro.monumentalBuildings.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.apache.jena.query.ResultSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.madridonyou.micro.domain.inputs.MailInput;
import com.madridonyou.micro.domain.inputs.RouteDefinition;
import com.madridonyou.micro.domain.outputs.MailOutput;
import com.madridonyou.micro.domain.rdf.Building;
import com.madridonyou.micro.domain.trace.TraceGenerator;
import com.madridonyou.micro.queryEngine.sparql.QueryExecutor;
import com.madridonyou.micro.queryEngine.thread.Worker;

@Controller
public class MonumentalBuildingsController implements MonumentalBuildingsAPI {

	public ResponseEntity<List<Building>> randomRoute(@RequestBody RouteDefinition def) {

		TraceGenerator trace = new TraceGenerator();

		long time_start, time_end;
		long time_startQuery, time_endQuery;
		long time_startOsm, time_endOsm;

		time_start = System.currentTimeMillis();
		List<Building> elements = new LinkedList<Building>();
		HttpStatus responseStatus = HttpStatus.OK;
		trace.commitQueryStart();
		QueryExecutor qExec = new QueryExecutor(def);
		time_startQuery = System.currentTimeMillis();
		ResultSet results = qExec.executeQuery();
		trace.setQueryRdf(qExec.getQuery(), def);
		trace.commitQueryEnd();
		time_endQuery = System.currentTimeMillis();

		List<Worker> workers = new LinkedList<Worker>();
		time_startOsm = System.currentTimeMillis();
		while (results.hasNext())
		{
			workers.add(new Worker(results.nextSolution(), def));
			workers.get(workers.size()-1).start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < workers.size(); i++)
		{
			try {
				workers.get(i).join();
				if (workers.get(i).getElement() != null) 
					elements.add(workers.get(i).getElement());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		trace.commitOSMEnd();
		time_endOsm = System.currentTimeMillis();

		time_end = System.currentTimeMillis();
		System.out.println("Time Overall "+ ( time_end - time_start ) +" milliseconds");
		System.out.println("Time Query "+ ( time_endQuery - time_startQuery ) +" milliseconds");
		System.out.println("Time OSM "+ ( time_endOsm - time_startOsm ) +" milliseconds");

		if (def.getEmail() != null && !def.getEmail().equals("") && isValidEmailAddress(def.getEmail()))
		{
			RestTemplate template = new RestTemplate();
			MailInput request = new MailInput();
			request.setSubject("Tu ruta generada por Madrid on You");
			request.setEmail(def.getEmail());
			request.setBody(trace.getTrace());
			try {
				template.postForObject(new URI("http://localhost:8088/sendMail"), request, MailOutput.class);
			} catch (RestClientException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<Building>>(elements,responseStatus);
	}


	private static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}
}
