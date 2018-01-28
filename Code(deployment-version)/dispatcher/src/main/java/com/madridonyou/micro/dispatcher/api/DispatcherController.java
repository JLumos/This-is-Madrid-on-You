package com.madridonyou.micro.dispatcher.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.madridonyou.micro.domain.dynamoTables.User;
import com.madridonyou.micro.domain.outputs.LoginOutput;
import com.madridonyou.micro.domain.outputs.RegisterOutput;
import com.madridonyou.micro.domain.rdf.Building;

@Controller
public class DispatcherController implements DispatcherAPI {

	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<?> forward(@Valid @RequestBody Object request) {

		RestTemplate template = new RestTemplate();

		if (request instanceof LinkedHashMap<?, ?>)
		{
			if (((LinkedHashMap<Object,Object>) request).size() == 3) 
			{
				LoginOutput result = new LoginOutput();
				try {
					result = template.postForObject(new URI("http://localhost:8084/login"), request, LoginOutput.class);
				} catch (RestClientException | URISyntaxException e) {
					e.printStackTrace();
				}
				return ResponseEntity.ok(result);
			}

			else if (((LinkedHashMap<Object,Object>) request).size() == 1) 
			{
				User result = new User();
				try {
					result = template.getForObject((new URI("http://localhost:8084/getInfo/"+((LinkedHashMap<Object,Object>) request).get("username"))), User.class);
				} catch (RestClientException | URISyntaxException e) {
					e.printStackTrace();
				}
				return ResponseEntity.ok(result);
			}
			
			else if (((LinkedHashMap<Object,Object>) request).size() == 5 && ((LinkedHashMap<Object,Object>) request).containsKey("update")) 
			{
				RegisterOutput result = new RegisterOutput();
				if (((LinkedHashMap<Object,Object>) request).get("update").equals(true)) 
				{
					try {
						result = template.postForObject(new URI("http://localhost:8084/update"), request, RegisterOutput.class);
					} catch (RestClientException | URISyntaxException e) {
						e.printStackTrace();
					}
				}

				else 
				{
					try {
						result = template.postForObject(new URI("http://localhost:8084/register"), request, RegisterOutput.class);
					} catch (RestClientException | URISyntaxException e) {
						e.printStackTrace();
					}
				}
				return ResponseEntity.ok(result);
			}

			else if (((LinkedHashMap<Object,Object>) request).size() == 6) 
			{
				Building [] result = null;
				try {
					result = template.postForObject(new URI("http://localhost:8086/random"), request, Building[].class);
				} catch (RestClientException | URISyntaxException e) {
					e.printStackTrace();
				}
				return ResponseEntity.ok(result);
			}
			
			else 
			{
				return ResponseEntity.ok("Unrecognized mapping");
			}
		}

		else 
		{
			return ResponseEntity.ok("Unrecognized body");
		}
	}

}
