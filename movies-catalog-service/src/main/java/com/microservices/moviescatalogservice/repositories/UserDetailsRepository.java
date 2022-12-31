package com.microservices.moviescatalogservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.moviescatalogservice.bean.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

	Optional<UserDetails> findByUserId(String userId);
}
