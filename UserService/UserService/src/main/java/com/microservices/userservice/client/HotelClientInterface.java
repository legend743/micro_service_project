package com.microservices.userservice.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.userservice.userentity.Hotel;

@FeignClient(name="HOTEL-MICROSERVICES", fallback =HotelClientFallback.class)
public interface HotelClientInterface {
    @GetMapping("/hoteldetails/gethotels/{id}")
    Optional<Hotel> getHotels(@PathVariable int id);


}
