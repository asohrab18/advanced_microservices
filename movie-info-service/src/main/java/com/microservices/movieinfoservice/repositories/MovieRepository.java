package com.microservices.movieinfoservice.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.movieinfoservice.bean.Movie;

@Transactional
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Optional<Movie> findByMovieId(String movieId);

	@Query(value = "SELECT * FROM dbo.movie WHERE id IN (SELECT movie_id_fk FROM dbo.rating WHERE user_id = :userId)", nativeQuery = true)
	Optional<List<Movie>> findAllByUserId(@Param("userId") String userId);
}
