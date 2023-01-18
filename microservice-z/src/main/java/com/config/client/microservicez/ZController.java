package com.config.client.microservicez;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("microservice-z")
public class ZController {

	@Value("${web.msg: Default Z Message}")
	private String data;

	@GetMapping("/messages")
	public String showMessage() {
		return data;
	}

}
