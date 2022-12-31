package com.microservices.movieinfoservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.movieinfoservice.bean.Movie;
import com.microservices.movieinfoservice.repositories.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Autowired
	private MovieRepository movieRepository;
	
	@GetMapping("/{id}")
	public Movie getMovie(@PathVariable("id") Integer id) {
		Optional<Movie>optMovie = movieRepository.findById(id);
		if(!optMovie.isPresent()) {
			return new Movie(id,"Default", "Default Movie name", "Default Movie");
		}
		return optMovie.get();
	}
	
	@GetMapping("/movie-id/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		Optional<Movie>optMovie = movieRepository.findByMovieId(movieId);
		if(!optMovie.isPresent()) {
			return new Movie(0, movieId, "Default Movie name", "Default Movie");
		}
		return optMovie.get();
	}
}
