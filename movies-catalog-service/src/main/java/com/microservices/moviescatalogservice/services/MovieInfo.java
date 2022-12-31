package com.microservices.moviescatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviescatalogservice.bean.CatalogItems;
import com.microservices.moviescatalogservice.bean.Movie;
import com.microservices.moviescatalogservice.bean.Rating;
import com.microservices.moviescatalogservice.bean.UserMovies;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemp;

	@Value("${movie.apiurl}")
	private String movieApiUrl;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItems", threadPoolKey = "movieInfoPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "20"), @HystrixProperty(name = "maxQueueSize", value = "10") })
	public CatalogItems getCatalogItems(Rating rating) {
		Movie movie = restTemp.getForObject(movieApiUrl + rating.getMovieIdFk(), Movie.class);
		return new CatalogItems(movie.getMovieName(), movie.getDescription(), rating.getRating());
	}

	public CatalogItems getFallbackCatalogItems(Rating rating) {
		return new CatalogItems("Movie name not found", "", rating.getRating());
	}

	public UserMovies getUserMovies(String userId) {
		UserMovies userMovies = restTemp.getForObject(movieApiUrl + "user-id/" + userId, UserMovies.class);
		return userMovies;
	}
}
