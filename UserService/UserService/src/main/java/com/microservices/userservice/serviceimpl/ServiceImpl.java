package com.microservices.userservice.serviceimpl;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.kafKaTemplate.core.KafkaTemplate;

import com.microservices.userservice.client.HotelClientInterface;
import com.microservices.userservice.client.RatingClientInterface;
import com.microservices.userservice.dto.UserProfileResponseDto;
import com.microservices.userservice.dto.UserRatingDto;
import com.microservices.userservice.dto.UserRequestDto;
import com.microservices.userservice.dto.UserResponseDto;
import com.microservices.userservice.event.UserServicePayload;
import com.microservices.userservice.event.UserSubmissionEvent;
import com.microservices.userservice.exception.InvalidRequesPassParameter;
import com.microservices.userservice.exception.ResourceNotFoundException;
import com.microservices.userservice.repository.UserRepository;
import com.microservices.userservice.service.UserService;
import com.microservices.userservice.userentity.Hotel;
import com.microservices.userservice.userentity.Rating;
import com.microservices.userservice.userentity.UserEntity;

@Service

public class ServiceImpl implements UserService {

	@Autowired

	UserRepository userrepo;
	//
	// @Autowired
	// private RestTemplate resttemplate;
	@Autowired
	private RatingClientInterface ratingClientInterface;
	@Autowired
	private HotelClientInterface hotelClientInterface;
	@Autowired 
	private KafkaTemplate<String,UserSubmissionEvent> kaftemplate;

	private final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

	@Override
	public UserResponseDto createuser(UserRequestDto user) {
		if (userrepo.existsById(user.getEmail()))
			throw new ResourceNotFoundException("user email already exists");
		UserEntity u = new UserEntity();
		u.setUserId(UUID.randomUUID().toString());
		u.setName(user.getName());
		u.setAbout(user.getAbout());
		u.setEmail(user.getEmail());
		u.setCreatedAt(LocalDateTime.now());
		UserEntity saved = userrepo.save(u);

		UserSubmissionEvent use = new UserSubmissionEvent();
		use.setEventId(UUID.randomUUID().toString());
		use.setCorealtionId(UUID.randomUUID().toString());
		use.setEventType("User-registed");
		use.setTimestamp(Instant.now());
		use.setUserServicePayload(setPayload(saved));

		kaftemplate.send(
			"user-registered",
			saved.getUserId(),
			use
		).get(10,TimeUnit.SECONDS);

		return toResponse(saved);
	}

	@Override
	public boolean isUserExists(String userId) {
		if (userId.isEmpty()) {
			throw new InvalidRequesPassParameter();
		}
		return userrepo.existsById(userId);

	}

	@Override
	public ResponseEntity<UserProfileResponseDto> getProfile(String Id) {
		if (!userrepo.existsById(Id)) {
			throw new ResourceNotFoundException("user with given Id is not found!");
		}
		Optional<UserEntity> user = userrepo.findById(Id);

		UserResponseDto urd = toResponse(user.get());
		UserProfileResponseDto retuResponseDto = new UserProfileResponseDto(urd, returnListOfUserRatedHotel(Id),
				"fetched");

		return new ResponseEntity<UserProfileResponseDto>(retuResponseDto, HttpStatus.OK);

	}

	@Override
	public List<UserResponseDto> getAllUsers() {

		return userrepo.findAll().stream().map(this::toResponse).collect(Collectors.toList());
	}

	@Override
	public UserEntity getUser(String userId) {
		// Fetch user details from the repository
		UserEntity userDetails = userrepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User is not found with given ID: " + userId));

		// Connect to rating service
		// String url = "http://localhost:8082/hotelrating/getbyusers/" + userId;

		try {
			// Logging the URL
			logger.info("Making REST call to: {}");

			// // Fetch list of ratings from the rating service
			// ResponseEntity<List<Rating>> responseEntity = resttemplate.exchange(
			// url,
			// HttpMethod.GET,
			// null,
			// new ParameterizedTypeReference<List<Rating>>() {
			// });
			List<Rating> responseEntity = ratingClientInterface.getRatingByUserId(userId);

			List<Rating> ratingList = responseEntity;

			// For each rating, fetch corresponding hotel details and set it to the rating
			List<Rating> updatedRatingList = ratingList.stream().map(rating -> {
				// String hotelUrl = "http://localhost:8081/hoteldetails/gethotels/" +
				// rating.getHotelId();
				Optional<Hotel> hotel = hotelClientInterface.getHotels(rating.getHotelId());
				// API call to hotel service
				// ResponseEntity<Hotel> hotelResponseEntity = resttemplate.exchange(
				// hotelUrl,
				// HttpMethod.GET,
				// null,
				// new ParameterizedTypeReference<Hotel>() {
				// } // Corrected type reference
				// );

				// Hotel hotel = hotelResponseEntity.getBody();
				// Set the hotel service response to the rating
				// rating.setHotel(hotel);

				// Return the updated rating
				rating.setHotel(hotel.get());
				return rating;
			}).collect(Collectors.toList());

			// Set the updated ratings list to the user
			userDetails.setRatings(updatedRatingList);

			// Logging the response
			logger.info("Response from rating service: {}", updatedRatingList);

		} catch (RestClientException e) {
			logger.error("Error occurred while making REST call: {}", e.getMessage(), e);
			throw new RuntimeException("Failed to fetch ratings for user ID: " + userId);
		}

		return userDetails;
	}

	private UserResponseDto toResponse(UserEntity savedUserEntity) {
		UserResponseDto ud = new UserResponseDto();
		ud.setUserId(savedUserEntity.getUserId());
		ud.setAbout(savedUserEntity.getAbout());
		ud.setEmail(savedUserEntity.getEmail());
		ud.setName(savedUserEntity.getName());
		return ud;

	}

	private List<UserRatingDto> returnListOfUserRatedHotel(String userId) {

		// will pass rating from rating service
		List<Rating> rating = ratingClientInterface.getRatingByUserId(userId);

		List<UserRatingDto> ratingDtos = rating.stream().map(this::toRatingDto).collect(Collectors.toList());
		return ratingDtos;

	}

	private UserRatingDto toRatingDto(Rating rating) {
		UserRatingDto dto = new UserRatingDto();

		dto.setUserId(rating.getUserId());
		dto.setHotelId(rating.getHotelId());
		dto.setRatingId(rating.getRatingId());
		dto.setRating(rating.getRating());
		dto.setFeedback(rating.getFeedback());
		dto.setHotel(rating.getHotel());
		return dto;

	}

	private UserServicePayload setPayload(UserEntity userEntity) {
		UserServicePayload up = new UserServicePayload();
		up.setUserId(userEntity.getUserId());
		return up;
	}
}
