package com.microservices.moviescatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviescatalogservice.bean.Rating;
import com.microservices.moviescatalogservice.bean.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingsInfo {

	@Autowired
	private RestTemplate restTemp;

	@Value("${ratings.apiurl}")
	private String ratingsApiUrl;

	@HystrixCommand(fallbackMethod = "getFallbackUserRatings", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") })
	public UserRatings getUserRatings(String userId) {
		return restTemp.getForObject(ratingsApiUrl + userId, UserRatings.class);
	}

	public UserRatings getFallbackUserRatings(String userId) {
		UserRatings userRatings = new UserRatings();
		userRatings.setRatings(Arrays.asList(new Rating(13, 13, 0, userId)));
		return userRatings;
	}
}
