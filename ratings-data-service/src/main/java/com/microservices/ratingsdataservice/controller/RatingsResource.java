package com.microservices.ratingsdataservice.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.ratingsdataservice.bean.Rating;
import com.microservices.ratingsdataservice.bean.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@GetMapping("/{userId}")
	public UserRatings getRatings(@PathVariable("userId") String userId) {
		UserRatings userRatings = new UserRatings();
		if (userId.equals("alams1")) {
			userRatings.setRatings(Arrays.asList(new Rating("DDLJ", 3), new Rating("KGF", 4), new Rating("SM", 5)));
		} else if (userId.equals("babydoll")) {
			userRatings.setRatings(Arrays.asList(new Rating("A", 2), new Rating("B", 3), new Rating("R", 5)));
		}
		return userRatings;
	}
}
