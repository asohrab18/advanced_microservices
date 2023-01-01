package com.microservices.moviescatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviescatalogservice.bean.Movie;
import com.microservices.moviescatalogservice.bean.UserMovies;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemp;

	@Value("${movie.apiurl}")
	private String movieApiUrl;

	@HystrixCommand(fallbackMethod = "getFallbackUserMovies", threadPoolKey = "movieInfoPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "20"), @HystrixProperty(name = "maxQueueSize", value = "10") })
	public UserMovies getUserMovies(String userId) {
		UserMovies userMovies = restTemp.getForObject(movieApiUrl + "user-id/" + userId, UserMovies.class);
		return userMovies;
	}

	public UserMovies getFallbackUserMovies(String userId) {
		return new UserMovies(userId, Arrays.asList(new Movie(13, "Default Id", "Default Name", "Default Movie")));
	}
}
