package com.pojo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Cart cart;
	
	@OneToOne
	private User user;
	
	@OneToOne
	private Payment payment;
	
	private String address;
	
	private LocalDateTime orderDate;
	
	// getter and setters

	public Integer getId() {
		return id;
	}

	public Cart getCart() {
		return cart;
	}

	public User getUser() {
		return user;
	}

	public Payment getPayment() {
		return payment;
	}

	public String getAddress() {
		return address;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	
	

}
