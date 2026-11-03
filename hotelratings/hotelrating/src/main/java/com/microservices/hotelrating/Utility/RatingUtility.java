package com.microservices.hotelrating.Utility;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class RatingUtility {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratingId;
	private int hotelId;
	private String userId;
	private int rating;
	private String feedback;
	private LocalDateTime createdAt;

	@Transient
	private Hotel hotel;


}
