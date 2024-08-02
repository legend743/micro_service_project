package com.microservices.hotelrating.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.hotelrating.Utility.RatingUtility;



@Repository
public interface RatingRepository extends JpaRepository<RatingUtility,Long> {


	
//	List<RatingUtility>findByUserID(String userId);
//	   List<RatingUtility> findByHotelId(String hotelId);
}
