package com.microservices.userservice.userentity;

public class Rating {
	
	
	
	private String userId;
	private String hotelId;
	private String ratingId;
	private int rating;
	private  String feedback;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getRatingId() {
		return ratingId;
	}
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
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
	public Rating(String userId, String hotelId, String ratingId, int rating, String feedback) {
		super();
		this.userId = userId;
		this.hotelId = hotelId;
		this.ratingId = ratingId;
		this.rating = rating;
		this.feedback = feedback;
	}
	public Rating() {
		super();
		
	}


}
