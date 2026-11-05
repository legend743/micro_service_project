package com.microservices.hotelrating.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {

    // @NotBlank
    // public Long ratingId;
    @NotBlank
    public String userId;
    @Min(1)
    public int hotelId;
    @Min(1)
    @Max(5)
    public int rating;
    @Size(min = 5, max = 500)
    public String feedback;

}
