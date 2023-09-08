package com.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cartItem")
public interface CartItemRest {
	
	@PostMapping("/add") 
	public ResponseEntity<String> addToCartItem(String bookId);

}
