package com.microservices.hotelrating.serviceimpl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.hotelrating.Utility.RatingUtility;
import com.microservices.hotelrating.client.UserClientInterface;
import com.microservices.hotelrating.dto.RatingRequest;
import com.microservices.hotelrating.dto.RatingResponseDto;
import com.microservices.hotelrating.exception.ResourceNotFoundException;
import com.microservices.hotelrating.repository.RatingRepository;
import com.microservices.hotelrating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingrepo;
	@Autowired
	UserClientInterface userClientInterface;

	@Override
	public ResponseEntity<String> created(RatingRequest rating) {
		if (!userClientInterface.isUserExists(rating.userId)) {
			throw new ResourceNotFoundException("user with user id not found/User Service Down");
		}
		// TODO add check for hotel
		try {
			RatingUtility r = mapToRatingUtility(rating);
			if (r != null)
				ratingrepo.save(r);
			return ResponseEntity.ok("Saved successfully");
		} catch (Exception e) {
			throw new InternalError("Internal Server Error!");
		}

	}

	@Override
	public List<RatingResponseDto> getAllRating() {
		try {
			List<RatingUtility> r = ratingrepo.findAll();
			if (r.isEmpty()) {
				throw new ResourceNotFoundException("rating not found");

			}
			return mapToRatingResponseDto(r);
		} catch (Exception e) {
			throw new InternalError(e.getMessage());
		}

	}

	@Override
	public Optional<RatingUtility> getRatingByRatingId(Long ratingId) {
		return ratingrepo.findById(ratingId);
	}

	@Override
	public List<RatingUtility> getRatingByUserId(String userId) {

		return ratingrepo.findUserByUserId(userId);
	}

	private RatingUtility mapToRatingUtility(RatingRequest req) {

		RatingUtility rating = new RatingUtility();
		rating.setHotelId(req.getHotelId());
		rating.setUserId(req.getUserId());
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		return rating;

	}

	private List<RatingResponseDto> mapToRatingResponseDto(List<RatingUtility> allRating) {
		return allRating.stream().map(this::toMapDto).collect(Collectors.toList());
	}

	private RatingResponseDto toMapDto(RatingUtility rating) {
		RatingResponseDto ob = new RatingResponseDto();
		ob.setUserId(rating.getUserId());
		ob.setHotelId(rating.getHotelId());
		ob.setRatingId(rating.getRatingId());
		ob.setRating(rating.getRating());
		ob.setFeedback(rating.getFeedback());
		ob.setCreatedAt(rating.getCreatedAt());
		return ob;

	}

}
