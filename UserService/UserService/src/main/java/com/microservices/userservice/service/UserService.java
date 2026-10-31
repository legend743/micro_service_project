package com.microservices.userservice.service;

import java.util.List;

import com.microservices.userservice.dto.UserRequestDto;
import com.microservices.userservice.dto.UserResponseDto;
import com.microservices.userservice.userentity.UserEntity;

public interface UserService {
 UserResponseDto createuser(UserRequestDto user);
 List<UserEntity> getAllUsers();
 UserEntity getUser(String Userid);
 
}
