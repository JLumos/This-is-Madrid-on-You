package com.madridonyou.micro.front.api;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.madridonyou.awsUtils.dynamoDB.DynamoDBUtils;
import com.madridonyou.micro.domain.dynamoTables.MoyUsers;
import com.madridonyou.micro.domain.dynamoTables.User;
import com.madridonyou.micro.domain.inputs.LoginInput;
import com.madridonyou.micro.domain.inputs.RegisterInput;
import com.madridonyou.micro.domain.inputs.RouteDefinition;
import com.madridonyou.micro.domain.outputs.LoginOutput;
import com.madridonyou.micro.domain.outputs.RegisterOutput;
import com.madridonyou.micro.domain.rdf.Building;
import com.madridonyou.micro.front.utils.FrontUtils;

import gherkin.deps.com.google.gson.Gson;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

@Controller
public class FrontController implements FrontAPI{
	
	public ModelAndView index() {

		ModelAndView maw = new ModelAndView("index");
		String json ="[{\"lat\":40.488255, \"lon\":-3.684310}, {\"lat\":40.487595, \"lon\":-3.686479}]";
		maw.addObject("json", json);
		return maw;
	}

	public ResponseEntity<?> getUserViaAjax(@Valid @RequestBody LoginInput search) {

		LoginOutput result = new LoginOutput();
		RestTemplate template = new RestTemplate();

		try {
			result = template.postForObject(new URI("http://localhost:8082/forward"), search, LoginOutput.class);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@Override
	public ModelAndView home(@PathVariable("username") String username) {

		ModelAndView maw = new ModelAndView("home");
		maw.addObject("activeUsername",username);
		
		return maw;
	}

	@Override
	public ModelAndView osm(@PathVariable("username") String username) {
		ModelAndView maw = new ModelAndView("map-osm");
		String json ="[{\"latitude\":40.488255, \"longitude\":-3.684310, \"name\":\"uno\"}, "
				+ "{\"latitude\":40.487595, \"longitude\":-3.686479, \"name\":\"uno\"},"
				+ "{\"latitude\":40.4850195, \"longitude\":-3.685909, \"name\":\"uno\"}" + "]";
		maw.addObject("json", json);
		maw.addObject("activeUsername",username);

		return maw;
	}

	@Override
	public ResponseEntity<?> createUserViaAjax(@Valid @RequestBody RegisterInput search) {

		RegisterOutput result = new RegisterOutput();
		RestTemplate template = new RestTemplate();
		search.setUpdate(false);
		
		try {
			result = template.postForObject(new URI("http://localhost:8082/forward"), search, RegisterOutput.class);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@Override
	public ModelAndView profile(@PathVariable("username") String username) {
		ModelAndView maw = new ModelAndView("profile");
		maw.addObject("activeUsername",username);

		RestTemplate template = new RestTemplate();
		User result = new User();
		User request = new User();
		request.setUsername(username);
		try {
			result = template.postForObject(new URI("http://localhost:8082/forward"), request, User.class);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		maw.addObject("user",result);
		
		return maw;
	}

	@Override
	public ModelAndView tourist(@PathVariable("username") String username) {

		ModelAndView maw = new ModelAndView("tourist");
		maw.addObject("routeDefinition",new RouteDefinition());
		maw.addObject("activeUsername",username);
		return maw;
	}

	@Override
	public void help() {
		
		URL website = null;
		try {
			website = new URL("http://www.facso.uchile.cl/publicaciones/moebio/39/veras.pdf");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		ReadableByteChannel rbc = null;
		try {
			rbc = Channels.newChannel(website.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("information.html");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ModelAndView touristRandom(@PathVariable("username") String username, @ModelAttribute("routeDefinition") RouteDefinition def, BindingResult errors) {

		ModelAndView maw = new ModelAndView("map-osm");

		RestTemplate template = new RestTemplate();
		Building[] result = null;
		try {
			result = template.postForObject(new URI("http://localhost:8082/forward"), def, Building[].class);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}

		FrontUtils.processOSMElements(result);
		maw.addObject("monuments", result);
		Gson gson = new Gson();
		String json = gson.toJson(result);
		maw.addObject("json", json);
		maw.addObject("activeUsername", username);
		return maw;
	}

	@Override
	public ResponseEntity<?> updateUserViaAjax(@Valid @RequestBody RegisterInput search) {
		RegisterOutput result = new RegisterOutput();
		RestTemplate template = new RestTemplate();
		search.setUpdate(true);
		
		try {
			result = template.postForObject(new URI("http://localhost:8082/forward"), search, RegisterOutput.class);
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(result);
	}

	@Override
	public ModelAndView osmStandalone(String username) {
		ModelAndView maw = new ModelAndView("map-osm-standalone");

		maw.addObject("activeUsername",username);

		return maw;
	}
}
