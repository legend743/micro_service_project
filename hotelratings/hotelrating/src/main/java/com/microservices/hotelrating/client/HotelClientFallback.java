package com.microservices.hotelrating.client;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.hotelrating.dto.HotelDto;

@Component

public class HotelClientFallback implements HotelClientInterface {

    @Override
    public Optional<HotelDto> getHotels(@PathVariable int id) {


        return Optional.of(null);
    }

}
