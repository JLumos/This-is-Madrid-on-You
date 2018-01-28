package com.madridonyou.micro.mail.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.madridonyou.micro.domain.inputs.MailInput;
import com.madridonyou.micro.domain.outputs.MailOutput;

@RestController
public interface MailAPI {

	@RequestMapping(
			value ="/sendMail", 
			method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"}
			)
	public ResponseEntity<MailOutput> login (@RequestBody @Valid MailInput input);
}
