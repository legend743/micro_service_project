package com.microservices.hotelrating.dto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class HotelDto {
    private int Id;
    private String hotelName;
    private String location;
}
