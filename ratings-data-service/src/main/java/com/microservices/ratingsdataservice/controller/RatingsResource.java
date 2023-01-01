package com.microservices.ratingsdataservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.ratingsdataservice.bean.Rating;
import com.microservices.ratingsdataservice.bean.UserRatings;
import com.microservices.ratingsdataservice.repositories.RatingRepository;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@Autowired
	private RatingRepository ratingRepository;
	
	@GetMapping("/{userId}")
	public UserRatings getRatings(@PathVariable("userId") String userId) {
		UserRatings userRatings = new UserRatings();
		List<Rating> ratings = ratingRepository.findByUserId(userId);
		Optional<List<Rating>> ratingsOpt = Optional.ofNullable(ratings);
		if(!ratingsOpt.isPresent() || ratings.isEmpty()) {
			ratings = Arrays.asList(new Rating(0,0,0,userId));
		}
		userRatings.setRatings(ratings);
		return userRatings;
	}
}
