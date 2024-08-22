package com.microservices.userservice.serviceimpl;
import java.util.List;
import java.util.UUID;
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
	        UserEntity userDetails = userrepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User is not found with given ID: " + userId));
	        
	        // Connect to rating service
	        String url = "http://localhost:8082/hotelrating/getbyusers/user123";
	        String hotelUrl="localhost:8081/hoteldetails/gethotels/1";

	        try {
	            // Logging the URL
	            logger.info("Making REST call to: {}", url);

//	        Rating response=  resttemplate.getForObject(url, Rating.class);
	            ResponseEntity<List<Rating>> responseEntity = resttemplate.exchange(
	                    url,
	                    HttpMethod.GET,
	                    null,
	                    new ParameterizedTypeReference<List<Rating>>() {}
	                    
	                    
	                );
//	          List<Rating> ratingList= responseEntity.stream().map(rating->{
//	            	//api call hotel service
//	            	//set the hotel service to rating
//	            	//return the rating
//	        	  
//	            }).collec(Collectors.toList());
	            
	            
	            // Logging the response
	            List<Rating> ratings = responseEntity.getBody();
	        userDetails.setRatings(ratings);
	        
	            logger.info("Response from rating service: {}", ratings);

	           

	        } catch (RestClientException e) {
	            logger.error("Error occurred while making REST call: {}", e.getMessage(), e);
	            throw new RuntimeException("Failed to fetch ratings for user ID: " + userId);
	        }

	        return userDetails;
	    }
	
	
	


}
