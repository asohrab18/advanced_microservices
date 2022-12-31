package com.microservices.configclientservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("db")
public class DbSettings {

	private String connectionUrl;
	private String user;
	private String password;
	private Integer port;

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
