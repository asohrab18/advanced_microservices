package com.microservices.moviescatalogservice.bean;

import java.util.List;

public class MovieCatalogOfUser {

	private String userId;
	private String username;
	private String email;
	private List<CatalogItems> catalogItemsList;

	public MovieCatalogOfUser() {
	}

	public MovieCatalogOfUser(String userId, String username, String email, List<CatalogItems> catalogItemsList) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.catalogItemsList = catalogItemsList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CatalogItems> getCatalogItemsList() {
		return catalogItemsList;
	}

	public void setCatalogItemsList(List<CatalogItems> catalogItemsList) {
		this.catalogItemsList = catalogItemsList;
	}

}
