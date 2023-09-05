package com.pojo;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Ratings {
	
	@Id
	private Integer id;
	
	private User user;
	private String review;
	private Double rating;
    private LocalDate ratingDate;
    
    private Book book;
    
    // getters and setters
    
	public User getUser() {
		return user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public LocalDate getRatingDate() {
		return ratingDate;
	}
	public void setRatingDate(LocalDate ratingDate) {
		this.ratingDate = ratingDate;
	}
}
