package com.example.microservices.hotel_microservices.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.microservices.hotel_microservices.Entity.HotelEntity;
import com.example.microservices.hotel_microservices.service.MicroService;

@RestController
@RequestMapping("/hoteldetails")
public class HotelController {

    @Autowired
    private MicroService microserve;

    @PostMapping("/addhotel")
    public ResponseEntity<HotelEntity> addHotels(@RequestBody HotelEntity hotelEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(microserve.Create(hotelEntity));
    }

    @GetMapping("/gethotels/{id}")
    public Optional<HotelEntity> getHotels(@PathVariable int id){
        return microserve.get(id);
    }
}
