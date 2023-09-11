package com.pojo;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany
	private List<CartItem> cartItems;
	
	@OneToOne
	private User user;

	// getter and setters
	
	public Integer getId() {
		return id;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public User getUser() {
		return user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	
	
}

