package com.microservices.ratingsdataservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.ratingsdataservice.bean.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	List<Rating> findByUserId(String userId);

}
