package com.microservices.userservice.userentity;

public class Hotel {
	

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
	public Hotel(int id, String hotelName, String location) {
		super();
		Id = id;
		this.hotelName = hotelName;
		this.location = location;
	}
	public Hotel() {
		super();
	
	}
    
    

}
