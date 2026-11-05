package com.microservices.hotelrating.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
// import org.springframework.kafka.core.KafKaTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.hotelrating.Utility.RatingUtility;
import com.microservices.hotelrating.client.UserClientInterface;
import com.microservices.hotelrating.dto.RatingRequest;
import com.microservices.hotelrating.dto.RatingResponseDto;
import com.microservices.hotelrating.event.RatingEventPayload;
import com.microservices.hotelrating.exception.ResourceNotFoundException;
import com.microservices.hotelrating.exception.BadRequestException;
import com.microservices.hotelrating.repository.RatingRepository;
import com.microservices.hotelrating.service.RatingService;
import com.microservices.hotelrating.event.RatingSubmittedEvent;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingrepo;
	@Autowired
	UserClientInterface userClientInterface;
	@Autowired
	private KafkaTemplate<String, RatingSubmittedEvent> kafKaTemplate;

	@Override
	public ResponseEntity<String> created(RatingRequest rating) {
		if (!userClientInterface.isUserExists(rating.userId)) {
			throw new ResourceNotFoundException("user with user id not found/User Service Down");
		}
		// TODO add check for hotel

		RatingUtility r = mapToRatingUtility(rating);

		RatingUtility ratingUtility = ratingrepo.save(r);
		// create event payload here
		RatingEventPayload eventPayload = new RatingEventPayload();
		eventPayload.setHotelId(0);
		eventPayload.setRatingId(ratingUtility.getRatingId());
		eventPayload.setUserId(rating.userId);
		eventPayload.setScore(rating.rating);

		RatingSubmittedEvent ratingSubmission = new RatingSubmittedEvent(
				UUID.randomUUID().toString(),
				"RATING_SUBMITTED",
				java.time.Instant.now(),
				UUID.randomUUID().toString(),
				eventPayload

		);

		try {
			kafKaTemplate.send(
					"rating.submitted",
					String.valueOf(ratingUtility.getRatingId()),
					ratingSubmission).get(10, TimeUnit.SECONDS);
			return ResponseEntity.ok("Saved successfully");
		}

		catch (Exception e) {
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
	public RatingResponseDto getRatingByRatingId(Long ratingId) {
		if (ratingId == null)
			throw new BadRequestException();
		RatingUtility rating = ratingrepo.findById(ratingId)
				.orElseThrow(() -> new ResourceNotFoundException("rating with given id is not found"));

		return (toMapDto(rating));
	}

	@Override
	public List<RatingResponseDto> getRatingByUserId(String userId) {

		List<RatingUtility> allRating = ratingrepo.findAllRatingUtilityByUserId(userId);
		if (allRating.isEmpty()) {
			throw new ResourceNotFoundException("Rating now found with this userId:" + userId);

		}
		List<RatingResponseDto> allRatingResponseList = allRating.stream().map(this::toMapDto)
				.collect(Collectors.toList());
		return allRatingResponseList;
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
