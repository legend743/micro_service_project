package com.microservices.userservice.dto;

import java.util.List;

public class UserProfileResponseDto {
    private UserResponseDto user;
    private List<UserRatingDto>ratings;
    private String message;

}
