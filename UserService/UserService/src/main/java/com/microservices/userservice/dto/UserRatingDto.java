package com.microservices.userservice.dto;

public class UserRatingDto {
    private Long ratingId;
    private String userId;
    private Long hotelId;
    private int rating;
    private String feedback;
    private HotelDto hotel;

}
