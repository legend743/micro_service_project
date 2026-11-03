package com.microservices.hotelrating.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.hotelrating.dto.HotelDto;

@FeignClient(name = "Hotel-Service", fallback = HotelClientFallback.class)
public interface HotelClientInterface {

    @GetMapping("/gethotels/{id}")
    Optional<HotelDto> getHotels(@PathVariable int id);

}
