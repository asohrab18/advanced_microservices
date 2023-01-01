package com.microservices.webclientservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.webclientservice.bean.UserMovies;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/web-info")
public class WebController {

	@Autowired
	private WebClient.Builder webClientBuilder;

	@GetMapping("user-id/{userId}")
	public Mono<UserMovies> getMovies(@PathVariable("userId") String userId) {
		Mono<UserMovies> userMoviesMono = webClientBuilder.build().get()
				.uri("http://localhost:8082/movies/user-id/" + userId)
				.retrieve().bodyToMono(UserMovies.class);
		return userMoviesMono;
	}
}
