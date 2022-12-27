package com.microservices.moviescatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviescatalogservice.bean.CatalogItems;
import com.microservices.moviescatalogservice.bean.Movie;
import com.microservices.moviescatalogservice.bean.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemp;
	
	@Value("${movie.apiurl}")
	private String movieApiUrl;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItems")
	public CatalogItems getCatalogItems(Rating rating) {
		Movie movie = restTemp.getForObject(movieApiUrl + rating.getMovieId(), Movie.class);
		return new CatalogItems(movie.getMovieName(), movie.getDescription(), rating.getRating());
	}
	
	public CatalogItems getFallbackCatalogItems(Rating rating) {
		return new CatalogItems("Movie name not found", "", rating.getRating());
	}
}
