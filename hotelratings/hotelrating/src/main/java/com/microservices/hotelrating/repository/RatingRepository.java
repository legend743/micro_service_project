package com.microservices.hotelrating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.hotelrating.Utility.RatingUtility;



@Repository
public interface RatingRepository extends JpaRepository<RatingUtility,Long> {
	
	List<RatingUtility>findUserByUserId(String userId);
}
