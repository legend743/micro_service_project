package com.microservices.userservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
@Getter 
@Setter 

public class UserRequestDto {
    @NotBlank  private String name;
    @NotBlank @Email private String email;
    private String about; 


}
