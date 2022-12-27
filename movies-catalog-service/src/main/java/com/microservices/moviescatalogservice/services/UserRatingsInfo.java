package com.microservices.moviescatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviescatalogservice.bean.Rating;
import com.microservices.moviescatalogservice.bean.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingsInfo {

	@Autowired
	private RestTemplate restTemp;

	@Value("${ratings.apiurl}")
	private String ratingsApiUrl;

	@HystrixCommand(fallbackMethod = "getFallbackUserRatings")
	public UserRatings getUserRatings(String userId) {
		return restTemp.getForObject(ratingsApiUrl + userId, UserRatings.class);
	}

	public UserRatings getFallbackUserRatings(String userId) {
		UserRatings userRatings = new UserRatings();
		userRatings.setRatings(Arrays.asList(new Rating("Movie ID", 0)));
		return userRatings;
	}
}
