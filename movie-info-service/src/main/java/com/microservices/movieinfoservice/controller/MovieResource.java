package com.microservices.movieinfoservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.movieinfoservice.bean.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@GetMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		if (movieId.equalsIgnoreCase("SM")) {
			return new Movie(movieId.toUpperCase(), "Spider Man", "This is a Hollywood Movie.");
		} else if (movieId.equalsIgnoreCase("KGF")) {
			return new Movie(movieId.toUpperCase(), "KGF Chapter-1 & 2", "This is a Tollywood Movie Series.");
		} else if (movieId.equalsIgnoreCase("DDLJ")) {
			return new Movie(movieId.toUpperCase(), "Dilwale Dulhaniya Le Jayenge", "This is a Bollywood Movie.");
		}else if (movieId.equalsIgnoreCase("A")) {
			return new Movie(movieId.toUpperCase(), "Aakhen", "This is a Bollywood Movie.");
		}else if (movieId.equalsIgnoreCase("B")) {
			return new Movie(movieId.toUpperCase(), "Baazigar", "This is a Bollywood Movie.");
		}else if (movieId.equalsIgnoreCase("R")) {
			return new Movie(movieId.toUpperCase(), "RRR", "This is a Tollywood Movie.");
		}
		return null;
	}
}
