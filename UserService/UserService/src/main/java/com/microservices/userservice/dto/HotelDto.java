package com.microservices.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class HotelDto {
private int id;
private String hotelName,location;
private double hotelAverageRating;
private int ratingCount;
}
