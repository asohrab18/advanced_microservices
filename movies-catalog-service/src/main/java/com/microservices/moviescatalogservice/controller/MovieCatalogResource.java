package com.microservices.moviescatalogservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.moviescatalogservice.bean.CatalogItems;
import com.microservices.moviescatalogservice.bean.Movie;
import com.microservices.moviescatalogservice.bean.MovieCatalogOfUser;
import com.microservices.moviescatalogservice.bean.Rating;
import com.microservices.moviescatalogservice.bean.UserDetails;
import com.microservices.moviescatalogservice.bean.UserMovies;
import com.microservices.moviescatalogservice.bean.UserRatings;
import com.microservices.moviescatalogservice.repositories.UserDetailsRepository;
import com.microservices.moviescatalogservice.services.MovieInfo;
import com.microservices.moviescatalogservice.services.UserRatingsInfo;

@RestController
@RequestMapping("/catalogs")
public class MovieCatalogResource {

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private UserRatingsInfo userRatingsInfo;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@GetMapping("/{userId}")
	public MovieCatalogOfUser getMovieCatalog(@PathVariable("userId") String userId) {
		MovieCatalogOfUser movieCatalogOfUser = new MovieCatalogOfUser();
		movieCatalogOfUser.setUserId(userId);
		Optional<UserDetails> userDetailsOpt = userDetailsRepository.findByUserId(userId);
		if (userDetailsOpt.isPresent()) {
			movieCatalogOfUser.setUsername(userDetailsOpt.get().getUsername());
			movieCatalogOfUser.setEmail(userDetailsOpt.get().getEmail());
		}
		List<CatalogItems> catalogItemsList = new ArrayList<CatalogItems>();
		UserRatings userRatings = userRatingsInfo.getUserRatings(userId);
		List<Rating> ratings = userRatings.getRatings();
		Map<Integer, Movie> movieMap = new HashMap<Integer, Movie>();
		if (ratings != null && !ratings.isEmpty()) {
			UserMovies userMovies = movieInfo.getUserMovies(userId);
			if (userMovies != null) {
				List<Movie> movies = userMovies.getMovies();
				if (movies != null && !movies.isEmpty()) {
					movies.stream().forEach(m -> movieMap.put(m.getId(), m));
				}
			}
			catalogItemsList = ratings.stream().map(r -> {
				CatalogItems catalogItems = new CatalogItems();
				catalogItems.setRating(r.getRating());
				if (movieMap.containsKey(r.getMovieIdFk())) {
					Movie movie = movieMap.get(r.getMovieIdFk());
					catalogItems.setName(movie.getMovieName());
					catalogItems.setDesc(movie.getDescription());
				}
				return catalogItems;
			}).collect(Collectors.toList());
		}
		movieCatalogOfUser.setCatalogItemsList(catalogItemsList);
		return movieCatalogOfUser;
	}
}