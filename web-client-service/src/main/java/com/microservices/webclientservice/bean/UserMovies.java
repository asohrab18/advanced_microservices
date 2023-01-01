package com.microservices.webclientservice.bean;

import java.util.List;

public class UserMovies {

	private String userId;
	private List<Movie> movies;

	public UserMovies() {
	}

	public UserMovies(String userId, List<Movie> movies) {
		super();
		this.userId = userId;
		this.movies = movies;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

}
