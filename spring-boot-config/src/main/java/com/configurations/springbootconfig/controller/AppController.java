package com.configurations.springbootconfig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Value("${my.greeting}")
	private String greetingMessage;
	
	@Value("${app.name}")
	private String applicationName;
	
	@Value("${app.description}")
	private String applicationDesc;

	@GetMapping("/greetings")
	public String greetUser() {
		return greetingMessage;
	}
	
	@GetMapping("/app-details")
	public String showAppDetails() {
		return "Application Name: "+applicationName+"<br/>Description: "+applicationDesc;
	}
}
