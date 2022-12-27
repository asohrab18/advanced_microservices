package com.microservices.moviescatalogservice.bean;

import java.util.List;

public class MovieCatalogOfUser {

	private String userId;
	private List<CatalogItems> catalogItemsList;

	public MovieCatalogOfUser() {
	}

	public MovieCatalogOfUser(String userId, List<CatalogItems> catalogItemsList) {
		super();
		this.userId = userId;
		this.catalogItemsList = catalogItemsList;
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
