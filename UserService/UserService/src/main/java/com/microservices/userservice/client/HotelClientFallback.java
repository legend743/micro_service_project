package com.microservices.userservice.client;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.userservice.userentity.Hotel;

@Component 
public class HotelClientFallback implements HotelClientInterface {
    private static final Logger log=LoggerFactory.getLogger(HotelClientFallback.class);
    @Override 
    public Optional<Hotel> getHotels(@PathVariable int id){
    log.warn("Hotel service down");
    return Optional.of(null);    
    }


}
