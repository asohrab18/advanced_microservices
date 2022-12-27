package com.microservices.moviescatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.moviescatalogservice.bean.CatalogItems;
import com.microservices.moviescatalogservice.bean.MovieCatalogOfUser;
import com.microservices.moviescatalogservice.bean.Rating;
import com.microservices.moviescatalogservice.bean.UserRatings;
import com.microservices.moviescatalogservice.services.MovieInfo;
import com.microservices.moviescatalogservice.services.UserRatingsInfo;

@RestController
@RequestMapping("/catalogs")
public class MovieCatalogResource {

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private UserRatingsInfo userRatingsInfo;

	@GetMapping("/{userId}")
	public MovieCatalogOfUser getMovieCatalog(@PathVariable("userId") String userId) {
		MovieCatalogOfUser movieCatalogOfUser = new MovieCatalogOfUser();
		movieCatalogOfUser.setUserId(userId);
		List<CatalogItems> catalogItemsList = null;
		UserRatings userRatings = userRatingsInfo.getUserRatings(userId);
		List<Rating> ratings = userRatings.getRatings();
		if (ratings != null && !ratings.isEmpty()) {
			catalogItemsList = ratings.stream().map(rating -> movieInfo.getCatalogItems(rating))
					.collect(Collectors.toList());
		}
		movieCatalogOfUser.setCatalogItemsList(catalogItemsList);
		return movieCatalogOfUser;
	}
}