package com.madridonyou.micro.monumentalBuildings.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.madridonyou.micro.domain.inputs.RouteDefinition;

@Controller
public interface MonumentalBuildingsAPI {

	@RequestMapping(
			value ="/random", 
			method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"}
			)
	public ResponseEntity<?> randomRoute (RouteDefinition def);
}
