package com.microservices.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.userservice.dto.UserRequestDto;
import com.microservices.userservice.dto.UserResponseDto;
import com.microservices.userservice.service.UserService;
import com.microservices.userservice.userentity.UserEntity;

@RestController
@RequestMapping("/userdetails")
public class UserController {
	@Autowired
	private UserService userservice;

	@PostMapping("/add")
	public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto requestDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userservice.createuser(requestDto));

	}

	@GetMapping("/getuser/{id}")
	public ResponseEntity<UserEntity> getUserDetails(@PathVariable String id) {
		UserEntity userdetails = userservice.getUser(id);
		return ResponseEntity.ok(userdetails);
	}

	@GetMapping("/getallusers")
	public ResponseEntity<List<UserResponseDto>> getallUser() {
		List<UserResponseDto> allUserdetails = userservice.getAllUsers();
		return ResponseEntity.ok(allUserdetails);
	}

}
