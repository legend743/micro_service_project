package com.example.microservices.hotel_microservices.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="hoteldetails")
public class HotelEntity {
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	
	
    private int Id;
    private String hotelName;
    private String location;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public HotelEntity(int id, String hotelName, String location) {
		super();
		Id = id;
		this.hotelName = hotelName;
		this.location = location;
	}
	@Override
	public String toString() {
		return "HotelEntity [Id=" + Id + ", hotelName=" + hotelName + ", location=" + location + "]";
	}
	public HotelEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
	

	
    
}
