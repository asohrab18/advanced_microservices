package com.configurations.springbootconfig.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@Value("${my.greeting: default value bcoz property does not exist in its file.}")
	private String greetingMessage;

	@Value("${app.name}")
	private String applicationName;

	@Value("${app.description}")
	private String applicationDesc;

	@Value("Some static message")
	private String staticMessage;

	@Value("${my.list.values}")
	private List<String> listValues;

	@Value("#{${dbValues}}")
	private Map<String, String> dbValues;

	@GetMapping("/greetings")
	public String greetUser() {
		return greetingMessage + "<br/>" + staticMessage + "<br/>" + listValues+ "<br/>" + dbValues;
	}

	@GetMapping("/app-details")
	public String showAppDetails() {
		return "<b>Application Name:</b> " + applicationName + "<br/><b>Description:</b> " + applicationDesc;
	}
}
