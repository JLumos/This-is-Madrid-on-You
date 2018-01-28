package com.madridonyou.micro.dispatcher.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public interface DispatcherAPI {

	@RequestMapping(value = "/forward", 
			method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"}
			)
	public ResponseEntity<?> forward(Object request);
}
