package com.microservices.hotelrating.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.hotelrating.Utility.RatingUtility;
import com.microservices.hotelrating.service.RatingService;

@RestController
@RequestMapping("/hotelrating")

//localhost:8082/hotelrating
public class RatingController {
	private static final Logger logger= org.slf4j.LoggerFactory.getLogger(RatingController.class);
	@Autowired
	private RatingService ratingservice;
	
	@PostMapping
	public ResponseEntity<RatingUtility>createHotelRating(@RequestBody RatingUtility ratingutility){
		RatingUtility createdRating = ratingservice.created(ratingutility);
		System.out.println(ratingutility+"-------------------------------");
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingservice.created(ratingutility));
	}
	@GetMapping("/getallhotelrating")
	public ResponseEntity<List<RatingUtility>>getraings(){
		return ResponseEntity.ok(ratingservice.getAllRating());
	}
	@GetMapping("/getbyusers/{userId}")
	public ResponseEntity<Object> getRatingByUserid(@PathVariable String userId){
		logger.info("user detais userId:{}"+userId);
		return ResponseEntity.ok(ratingservice.getRatingByUserId(userId));
	}
	@GetMapping("/getByRatingId/{ratingId}")
	public ResponseEntity<Optional<RatingUtility>> getRatingByHotelId(@PathVariable Long ratingId){
		return ResponseEntity.ok(ratingservice.getRatingByRatingId(ratingId));
	}
}
