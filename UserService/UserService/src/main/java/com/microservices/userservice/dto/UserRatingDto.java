package com.microservices.userservice.dto;

import com.microservices.userservice.userentity.Hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class UserRatingDto {
    private Long ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;

}
