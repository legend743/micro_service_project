package com.microservices.userservice.userentity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="userdetails")
public class UserEntity {

	@Id
	private String userId;
	private String name;
	private String email;
	private String about;
	
	@Transient
	
	private List<Rating>ratings=new ArrayList<>();
	
	@Transient
	private Hotel hotel;
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}

	public UserEntity(String userId, String name, String email, String about, List<Rating> ratings, Hotel hotel) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.about = about;
		this.ratings = ratings;
		this.hotel = hotel;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", name=" + name + ", email=" + email + ", about=" + about + "]";
	}
	public List<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	public UserEntity(List<Rating> ratings) {
		super();
		this.ratings = ratings;
	}
	
}
