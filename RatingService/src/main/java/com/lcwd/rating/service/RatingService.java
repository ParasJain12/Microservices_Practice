package com.lcwd.rating.service;

import java.util.List;

import com.lcwd.rating.model.Rating;

public interface RatingService {

	Rating create(Rating rating);
	
	List<Rating> getRatings();
	List<Rating> getRatingByUserId(String userId);
	List<Rating> getRatingByHotelId(String hotelId);
}
