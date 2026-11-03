package com.microservices.userservice.client;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.userservice.userentity.Rating;

@Component 
public class RatingClientFallback implements RatingClientInterface{
    private static final Logger log=LoggerFactory.getLogger(RatingClientFallback.class);
    
    @Override 
    public List<Rating> getRatingByUserId(@PathVariable("userId") String userId){
         log.warn("Rating Service unavailable, returning empty ratings for user {}", userId);
        return List.of();
    }
    

}
