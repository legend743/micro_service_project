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
import com.microservices.hotelrating.dto.RatingRequest;
import com.microservices.hotelrating.dto.RatingResponseDto;
import com.microservices.hotelrating.service.RatingService;

@RestController
@RequestMapping("/hotelrating")

// localhost:8082/hotelrating
public class RatingController {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RatingController.class);
	@Autowired
	private RatingService ratingservice;

	@PostMapping
	public ResponseEntity<String> createHotelRating(@RequestBody RatingRequest ratingutility) {
		ResponseEntity<String> createdRating = ratingservice.created(ratingutility);
		System.out.println(ratingutility + "-------------------------------");
		return createdRating;
	}

	@GetMapping("/getallhotelrating")
	public ResponseEntity<List<RatingResponseDto>> getraings() {
		return ResponseEntity.ok(ratingservice.getAllRating());
	}

	@GetMapping("/getbyusers/{userId}")
	public ResponseEntity<List<RatingUtility>> getRatingByUserid(@PathVariable String userId) {
		logger.info("user detais userId:{}" + userId);
		return ResponseEntity.ok(ratingservice.getRatingByUserId(userId));
	}

	@GetMapping("/getByRatingId/{ratingId}")
	public ResponseEntity<Optional<RatingUtility>> getRatingByHotelId(@PathVariable Long ratingId) {
		return ResponseEntity.ok(ratingservice.getRatingByRatingId(ratingId));
	}
}
