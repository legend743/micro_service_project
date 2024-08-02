package controller;

import Entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userservices.Service;

import java.util.List;


// localhost:8090/userdetails/getuserdetails
@RestController
@RequestMapping("/userdetails")
public class UserController {
    @Autowired
    Service userservice;
    @PostMapping("/adduser")
    public ResponseEntity<UsersEntity> addUser(@RequestBody UsersEntity enity){
        return ResponseEntity.status(HttpStatus.CREATED).body(userservice.createUser(enity));

    }
//    @GetMapping("/getuserdetails{id}")
//    public ResponseEntity<UsersEntity> getuserById(@PathVariable int Id){
//        return ResponseEntity.status(HttpStatus.OK).body(userservice.getUser(Id));
//    }
    @GetMapping("/getuserdetails/{id}")
    public ResponseEntity<UsersEntity> getUserDetails(@PathVariable int id) {
        // Your logic to get user details by id
    	UsersEntity userDetails = userservice.getUser(id);
    	return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UsersEntity>>getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userservice.getAllDetails());
    }
}
