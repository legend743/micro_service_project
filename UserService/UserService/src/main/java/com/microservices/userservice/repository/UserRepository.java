package com.microservices.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.userservice.userentity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

}
