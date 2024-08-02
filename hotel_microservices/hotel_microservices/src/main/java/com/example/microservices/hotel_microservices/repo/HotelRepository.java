package com.example.microservices.hotel_microservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microservices.hotel_microservices.Entity.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity,Integer> {
}
