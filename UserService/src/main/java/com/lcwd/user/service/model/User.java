package com.lcwd.user.service.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "micro_users")
public class User {

	@Id
	@Column(name = "ID")
	private String userId;

	@Column(name = "NAME", length = 20)
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ABOUT")
	private String about;

	@Transient
	private List<Rating> ratings = new ArrayList<>();

	// Private constructor for builder
	private User(Builder builder) {
		this.userId = builder.userId;
		this.name = builder.name;
		this.email = builder.email;
		this.about = builder.about;
		this.ratings = builder.ratings;
	}

	// Default constructor (needed by JPA)
	public User() {
	}

	// Getters and setters
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

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	// Static builder() method
	public static Builder builder() {
		return new Builder();
	}

	// Static inner Builder class
	public static class Builder {
		private String userId;
		private String name;
		private String email;
		private String about;
		private List<Rating> ratings = new ArrayList<>();

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder about(String about) {
			this.about = about;
			return this;
		}

		public Builder ratings(List<Rating> ratings) {
			this.ratings = ratings;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
