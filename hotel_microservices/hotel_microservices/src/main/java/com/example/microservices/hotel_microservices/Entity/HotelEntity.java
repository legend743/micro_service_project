package com.example.microservices.hotel_microservices.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
@Table(name = "hoteldetails")
public class HotelEntity {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int Id;
	private String hotelName;
	private String location;

	
}
