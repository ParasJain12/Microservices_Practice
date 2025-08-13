package com.lcwd.user.service.model;

public class Rating {

    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
    private Hotel hotel;

    // Private constructor to force usage of builder
    private Rating(Builder builder) {
        this.ratingId = builder.ratingId;
        this.userId = builder.userId;
        this.hotelId = builder.hotelId;
        this.rating = builder.rating;
        this.feedback = builder.feedback;
        this.hotel = builder.hotel;
    }

    // Getters
    public String getRatingId() {
        return ratingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public int getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public Hotel getHotel() {
        return hotel;
    }
    
    

    public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	// Builder static method
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private String ratingId;
        private String userId;
        private String hotelId;
        private int rating;
        private String feedback;
        private Hotel hotel;

        public Builder ratingId(String ratingId) {
            this.ratingId = ratingId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder hotelId(String hotelId) {
            this.hotelId = hotelId;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder feedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public Builder hotel(Hotel hotel) {
            this.hotel = hotel;
            return this;
        }

        public Rating build() {
            return new Rating(this);
        }
    }
}
