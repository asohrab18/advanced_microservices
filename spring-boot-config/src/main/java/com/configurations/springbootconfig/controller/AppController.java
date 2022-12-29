package com.configurations.springbootconfig.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.configurations.springbootconfig.DbSettings;

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

	@Value("#{${address}}")
	private Map<String, String> addressLocation;

	@Autowired
	private DbSettings dbSettings;

	@Autowired
	private Environment environment;

	@GetMapping("/greetings")
	public String greetUser() {
		return greetingMessage + "<br/>" + staticMessage + "<br/>" + listValues + "<br/>Address: " + addressLocation;
	}

	@GetMapping("/app-details")
	public String showAppDetails() {
		return "<b>Application Name:</b> " + applicationName + "<br/><b>Description:</b> " + applicationDesc;
	}

	@GetMapping("/db-details")
	public String getDatabaseInfo() {
		return "<b>Connection URL:</b> " + dbSettings.getConnectionUrl() + "<br/><b>User:</b> " + dbSettings.getUser()
				+ "<br/><b>Password:</b> " + dbSettings.getPassword() + "<br/><b>Host:</b> " + dbSettings.getHost()
				+ "<br/><b>Port:</b> " + dbSettings.getPort();
	}

	@GetMapping("/env-details")
	public String getEnvironmentDetails() {
		return environment.toString();
	}
}
