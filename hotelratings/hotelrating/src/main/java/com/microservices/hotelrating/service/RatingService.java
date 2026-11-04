package com.microservices.hotelrating.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.microservices.hotelrating.Utility.RatingUtility;
import com.microservices.hotelrating.dto.RatingRequest;
import com.microservices.hotelrating.dto.RatingResponseDto;

public interface RatingService {
	// create
	ResponseEntity<String> created(RatingRequest rating);

	// getall ratings
	List<RatingResponseDto> getAllRating();

	// Object getRatingByUserId(Long userId);
	// List<RatingUtility> getRatingByUserId(Long ratingId);

	// Optional<RatingUtility> getRatingByRatingId(Long ratingId);
	 RatingResponseDto getRatingByRatingId(Long ratingId);

	List<RatingResponseDto> getRatingByUserId(String userId);

}
