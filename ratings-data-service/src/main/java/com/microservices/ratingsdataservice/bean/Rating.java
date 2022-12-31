package com.microservices.ratingsdataservice.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer movieIdFk;
	private Integer rating;
	private String userId;

	public Rating() {
	}

	public Rating(Integer id, Integer movieIdFk, Integer rating, String userId) {
		super();
		this.id = id;
		this.movieIdFk = movieIdFk;
		this.rating = rating;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMovieIdFk() {
		return movieIdFk;
	}

	public void setMovieIdFk(Integer movieIdFk) {
		this.movieIdFk = movieIdFk;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
