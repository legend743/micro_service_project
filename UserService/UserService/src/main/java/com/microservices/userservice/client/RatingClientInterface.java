package com.microservices.userservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.userservice.userentity.Rating;

@FeignClient(name = "HOTELRATING-SERVICE",fallback = RatingClientFallback.class)
public interface RatingClientInterface {
@GetMapping("/hotelrating/getbyusers/{userId}")
List<Rating> getRatingByUserId(@PathVariable("userId") String userId);

}
