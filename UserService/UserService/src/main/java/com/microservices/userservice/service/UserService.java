package com.microservices.userservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.microservices.userservice.dto.UserProfileResponseDto;
import com.microservices.userservice.dto.UserRequestDto;
import com.microservices.userservice.dto.UserResponseDto;
import com.microservices.userservice.userentity.UserEntity;

public interface UserService {
 UserResponseDto createuser(UserRequestDto user);
 List<UserEntity> getAllUsers();
 UserEntity getUser(String Userid);
 ResponseEntity<UserProfileResponseDto> getProfile(String Id);
 
}
