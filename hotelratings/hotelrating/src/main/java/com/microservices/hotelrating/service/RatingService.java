package com.microservices.hotelrating.service;

import java.util.List;
import java.util.Optional;

import com.microservices.hotelrating.Utility.RatingUtility;


public interface RatingService {
	//create 
	RatingUtility created(RatingUtility rating);
	
	//getall ratings
	List<RatingUtility> getAllRating();

//	Object getRatingByUserId(Long userId);
//	List<RatingUtility> getRatingByUserId(Long ratingId);

	Optional<RatingUtility> getRatingByRatingId(Long ratingId);

	Object getRatingByUserId(String userId);
	
		
	
	
	

}
