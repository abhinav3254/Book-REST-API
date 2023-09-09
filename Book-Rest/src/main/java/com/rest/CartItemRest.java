package com.rest;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.CartItem;

@RequestMapping("/cartItem")
public interface CartItemRest {
	
	@PostMapping("/add") 
	public ResponseEntity<String> addToCartItem(String bookId);
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CartItem>> getAllItemsFromCart();
	
//	For increase quantity in cartItem
	@PostMapping("/incrementItem") 
	public ResponseEntity<String> incrementItem(String cartItemId);

//	Decrease the quantity in the cartItem
	@PostMapping("/decrementItem") 
	public ResponseEntity<String> decrementItem(String cartItemId);
	
	// Delete Item from the cart
	
	@PostMapping("/deleteItem") 
	public ResponseEntity<String> deleteItem(String cartItemId);
	
	@GetMapping("/totalPriceSum")
	public ResponseEntity<Double> getSumOfAllFinalPrice();

	
}
