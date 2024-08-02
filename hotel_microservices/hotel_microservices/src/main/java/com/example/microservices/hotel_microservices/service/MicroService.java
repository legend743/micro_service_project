package com.example.microservices.hotel_microservices.service;

import java.util.List;
import java.util.Optional;

import com.example.microservices.hotel_microservices.Entity.HotelEntity;

public interface MicroService {
HotelEntity Create(HotelEntity hotel);
List<HotelEntity> getAll();
Optional<HotelEntity> get(int Id);
}
