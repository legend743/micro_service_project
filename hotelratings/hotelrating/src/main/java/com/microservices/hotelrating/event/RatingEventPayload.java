package com.microservices.hotelrating.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 

public class RatingEventPayload {

    private Long ratingId;
    private String userId;
    private int hotelId;
    private int score;

}
