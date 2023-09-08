package com.rest;


import java.util.List;

import org.springframework.http.ResponseEntity;
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

}
