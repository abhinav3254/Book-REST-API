package com.service;


import org.springframework.http.ResponseEntity;


public interface CartService {
	
	public ResponseEntity<String> addToCart();
	
}
