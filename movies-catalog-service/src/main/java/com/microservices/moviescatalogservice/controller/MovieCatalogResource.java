package com.microservices.moviescatalogservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviescatalogservice.bean.CatalogItems;
import com.microservices.moviescatalogservice.bean.Movie;
import com.microservices.moviescatalogservice.bean.MovieCatalogOfUser;
import com.microservices.moviescatalogservice.bean.Rating;
import com.microservices.moviescatalogservice.bean.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalogs")
public class MovieCatalogResource {

	@Value("${ratings.apiurl}")
	private String ratingsApiUrl;
	
	@Value("${movie.apiurl}")
	private String movieApiUrl;
	
	
	@Autowired
	private RestTemplate restTemp;

	@GetMapping("/{userId}")
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItems")
	public MovieCatalogOfUser getCatalogItems(@PathVariable("userId") String userId) {
		MovieCatalogOfUser movieCatalogOfUser = new MovieCatalogOfUser();
		movieCatalogOfUser.setUserId(userId);
		
		List<CatalogItems> catalogItemsList = null;
		UserRatings userRatings = restTemp.getForObject(ratingsApiUrl + userId, UserRatings.class);
		List<Rating> ratings = userRatings.getRatings();
		if (ratings != null && !ratings.isEmpty()) {
			catalogItemsList = ratings.stream().map(rating -> {
				Movie movie = restTemp.getForObject(movieApiUrl + rating.getMovieId(), Movie.class);
				return new CatalogItems(movie.getMovieName(), movie.getDescription(), rating.getRating());
			}).collect(Collectors.toList());
		}
		movieCatalogOfUser.setCatalogItemsList(catalogItemsList);
		return movieCatalogOfUser;
	}
	
	public MovieCatalogOfUser getFallbackCatalogItems(@PathVariable("userId") String userId) {
		MovieCatalogOfUser movieCatalogOfUser = new MovieCatalogOfUser();
		movieCatalogOfUser.setUserId(userId);
		movieCatalogOfUser.setCatalogItemsList(Arrays.asList(new CatalogItems("No Movie", "", 0)));
		return movieCatalogOfUser;
	}
}
