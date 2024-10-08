package com.microservices.userservice.serviceimpl;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
	@Autowired
	private RestTemplate resttemplate;
	
	private  final Logger logger=LoggerFactory.getLogger(ServiceImpl.class);

	@Override
	public UserEntity createuser(UserEntity user) {
		String randomUserId=UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userrepo.save(user);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}


	@Override
	public UserEntity getUser(String userId) {
	    // Fetch user details from the repository
	    UserEntity userDetails = userrepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User is not found with given ID: " + userId));

	    // Connect to rating service
	    String url = "http://localhost:8082/hotelrating/getbyusers/"+userId;
	  

	    try {
	        // Logging the URL
	        logger.info("Making REST call to: {}", url);

	        // Fetch list of ratings from the rating service
	        ResponseEntity<List<Rating>> responseEntity = resttemplate.exchange(
	                url,
	                HttpMethod.GET,
	                null,
	                new ParameterizedTypeReference<List<Rating>>() {}
	        );

	        List<Rating> ratingList = responseEntity.getBody();

	        // For each rating, fetch corresponding hotel details and set it to the rating
	        List<Rating> updatedRatingList = ratingList.stream().map(rating -> {
	        	  String hotelUrl = "http://localhost:8081/hoteldetails/gethotels/"+rating.getHotelId();
	            // API call to hotel service
	            ResponseEntity<Hotel> hotelResponseEntity = resttemplate.exchange(
	                    hotelUrl,
	                    HttpMethod.GET,
	                    null,
	                    new ParameterizedTypeReference<Hotel>() {}  // Corrected type reference
	            );

	            Hotel hotel = hotelResponseEntity.getBody();
	            // Set the hotel service response to the rating
	            rating.setHotel(hotel);

	            // Return the updated rating
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
	}}
	