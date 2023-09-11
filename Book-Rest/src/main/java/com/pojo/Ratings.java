package com.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Ratings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Double rating;
	
	@OneToOne
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ratingPostDate;
	
	// getter and setters

	public Integer getId() {
		return id;
	}

	public Double getRating() {
		return rating;
	}

	public User getUser() {
		return user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRatingPostDate() {
		return ratingPostDate;
	}

	public void setRatingPostDate(Date ratingPostDate) {
		this.ratingPostDate = ratingPostDate;
	}
	
}
