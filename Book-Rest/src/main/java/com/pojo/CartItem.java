package com.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;


@Data
@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Book book;
	private double bookPrice;
	private int quantity;
	
	private Double cgst;
	
	private Double sgst;
	
	private Double discount;
	
	private Double finalPrice;
	
	
	// getter and setters

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public Double getCgst() {
		return cgst;
	}

	public Double getSgst() {
		return sgst;
	}

	public Double getDiscount() {
		return discount;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	

	
	
	

}
