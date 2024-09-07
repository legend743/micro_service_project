package com.microservices.hotelrating.Utility;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity


public class RatingUtility {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratingId;
	private String hotelId;
	private String userId;
	private int rating;
	private String feedback;
	
	@Transient
	private Hotel hotel;
	
	
	
	
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Long getRatingId() {
		return ratingId;
	}
	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public RatingUtility(Long ratingId, String hotelId, String userId, int rating, String feedback,Hotel hotel) {
		super();
		this.ratingId = ratingId;
		this.hotelId = hotelId;
		this.userId = userId;
		this.rating = rating;
		this.feedback = feedback;
		this.hotel=hotel;
	}
	public RatingUtility() {
		super();
	
	}
	
	
	

}
