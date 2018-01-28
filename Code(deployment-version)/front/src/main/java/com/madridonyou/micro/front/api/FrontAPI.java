package com.madridonyou.micro.front.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.madridonyou.micro.domain.inputs.LoginInput;
import com.madridonyou.micro.domain.inputs.RegisterInput;
import com.madridonyou.micro.domain.inputs.RouteDefinition;

@RestController
public interface FrontAPI {

	@RequestMapping("/")
	public ModelAndView index();
	
	@RequestMapping("/tryLogin")
	public ResponseEntity<?> getUserViaAjax(LoginInput search);
	
	@RequestMapping("/tryRegister")
	public ResponseEntity<?> createUserViaAjax(RegisterInput search);
	
	@RequestMapping("/tryUpdate")
	public ResponseEntity<?> updateUserViaAjax(RegisterInput search);
	
	@RequestMapping("/users/{username}/home")
	public ModelAndView home(@PathVariable("username") String username);
	
	@RequestMapping("/users/{username}/map")
	public ModelAndView osm(@PathVariable("username") String username);

	@RequestMapping("/users/{username}/mapStandalone")
	public ModelAndView osmStandalone(@PathVariable("username") String username);	
	
	@RequestMapping("/users/{username}/profile")
	public ModelAndView profile(@PathVariable("username") String username);
	
	@RequestMapping("/users/{username}/routeDefinition")
	public ModelAndView tourist(@PathVariable("username") String username);
	
	@RequestMapping("/help")
	public void help();
	
	@RequestMapping("/users/{username}/myRoute")
	public ModelAndView touristRandom(@PathVariable("username") String username, RouteDefinition def, BindingResult errors);
	
}
