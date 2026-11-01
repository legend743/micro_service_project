package com.microservices.userservice.userentity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Rating {
	
	
	
	private String userId;
	private String hotelId;
	private Long ratingId;
	private int rating;
	private  String feedback;
	@Transient
	private Hotel hotel;
	
	
	
}