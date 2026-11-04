package com.example.microservices.hotel_microservices.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservices.hotel_microservices.Entity.HotelEntity;
import com.example.microservices.hotel_microservices.repo.HotelRepository;
import com.example.microservices.hotel_microservices.service.MicroService;
import com.example.microservices.hotel_microservices.dto.*;
import com.example.microservices.hotel_microservices.exception.*;

import java.util.List;
import java.util.Optional;

@Service

public class BusinessImpl implements MicroService {
    @Autowired
    HotelRepository hotelrepo;

    @Override
    public HotelEntity Create(HotelRequestDto hotel) {
        if(hotel==null)
            throw new BadRequestHandler("Please pass data");

        return hotelrepo.save(mapToHotelEntity(hotel));
    }

    @Override
    public List<HotelEntity> getAll() {
        return hotelrepo.findAll();
    }

    @Override
    public Optional<HotelEntity> get(int Id) {
        // TODO Auto-generated method stub
        return hotelrepo.findById(Id);
    }

    public HotelEntity mapToHotelEntity(HotelRequestDto hotelDto) {

        HotelEntity hotel = new HotelEntity();
        hotel.setHotelName(hotelDto.getHotelName());
        hotel.setLocation(hotelDto.getLocation());
        return hotel;

    }

}