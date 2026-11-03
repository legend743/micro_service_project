package com.microservices.hotelrating.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 

public class RatingResponseDto {
    public Long ratingId;

    public String userId;

    public int hotelId;

    public int rating;

    public String feedback;
    public LocalDateTime createdAt;

}
