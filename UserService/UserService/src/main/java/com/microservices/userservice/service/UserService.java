package com.microservices.userservice.service;

import java.util.List;

import com.microservices.userservice.userentity.UserEntity;

public interface UserService {
 UserEntity createuser(UserEntity user);
 List<UserEntity> getAllUsers();
 UserEntity getUser(String Userid);
 
}
