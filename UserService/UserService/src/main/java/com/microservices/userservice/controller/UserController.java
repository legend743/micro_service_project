package com.microservices.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.userservice.service.UserService;
import com.microservices.userservice.userentity.UserEntity;

@RestController
@RequestMapping("/userdetails")
public class UserController {
	@Autowired
	private UserService userservice;

	@PostMapping("/add")
	public ResponseEntity<UserEntity>addUser(@RequestBody UserEntity userentity){
		UserEntity adduser=userservice.createuser(userentity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(adduser);
		
	}
	
	@GetMapping("/getuser/{id}")
	public ResponseEntity<UserEntity>getUserDetails(@PathVariable String id){
		UserEntity userdetails=userservice.getUser(id);
		return ResponseEntity.ok(userdetails);
	}
	@GetMapping("/getallusers")
	public ResponseEntity<List<UserEntity>>getallUser(){
		List<UserEntity>allUserdetails=userservice.getAllUsers();
		return ResponseEntity.ok(allUserdetails);
	}
	
	
}
