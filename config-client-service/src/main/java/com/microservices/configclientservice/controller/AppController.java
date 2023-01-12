package com.microservices.configclientservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.configclientservice.DbSettings;

@RestController
@RefreshScope
public class AppController {

	@Value("${my.greeting: default value bcoz property does not exist in its file.}")
	private String greetingMessage;

	@Value("${my.list.values}")
	private List<String> listValues;

	@Autowired
	private DbSettings dbSettings;

	@GetMapping("/greetings")
	public String greetUser() {
		return greetingMessage + "<br/>" + listValues;
	}

	@GetMapping("/db-details")
	public String getDatabaseInfo() {
		return "<b>Connection URL:</b> " + dbSettings.getConnectionUrl() + "<br/><b>User:</b> " + dbSettings.getUser()
				+ "<br/><b>Password:</b> " + dbSettings.getPassword();
	}

}
