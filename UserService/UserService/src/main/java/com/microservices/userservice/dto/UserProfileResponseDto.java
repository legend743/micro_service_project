package com.microservices.userservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder 
@Getter 
@Setter 
@AllArgsConstructor 

public class UserProfileResponseDto {
    private UserResponseDto user;
    private List<UserRatingDto>ratings;
    private String message;

}
