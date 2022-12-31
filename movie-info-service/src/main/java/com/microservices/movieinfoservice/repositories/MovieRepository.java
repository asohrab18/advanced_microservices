package com.microservices.movieinfoservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.movieinfoservice.bean.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Optional<Movie> findByMovieId(String movieId);
}
