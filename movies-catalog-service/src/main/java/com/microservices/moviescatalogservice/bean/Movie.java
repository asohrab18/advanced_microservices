package com.microservices.moviescatalogservice.bean;

public class Movie {

	private Integer id;
	private String movieId;
	private String movieName;
	private String description;

	public Movie() {
	}

	public Movie(Integer id, String movieId, String movieName, String description) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.movieName = movieName;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

}
