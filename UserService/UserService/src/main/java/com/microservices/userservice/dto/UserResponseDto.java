package com.microservices.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class UserResponseDto {
    private String userId, name, email, about;

}
