package com.config.client.microservicex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("microservice-x")
public class XController {

	@Value("${app.message: Default X Message}")
	private String message;

	@GetMapping("/messages")
	public String showMessage() {
		return message;
	}
}
