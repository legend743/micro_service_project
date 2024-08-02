package com.microservices.hotelrating.service;

import java.util.List;


import com.microservices.hotelrating.Utility.RatingUtility;


public interface RatingService {
	//create 
	RatingUtility created(RatingUtility rating);
	
	//getall ratings
	List<RatingUtility> getAllRating();

	Object getRatingByUserId(Long userId);
	
		// get single hotel rating
//	List<RatingUtility>getRatingByUserId(String id);
//	
//	List<RatingUtility>getAllRatingByHotel(String hotelId);
	
	
	

}
