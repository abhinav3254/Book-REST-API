package com.service;

import org.springframework.http.ResponseEntity;

public interface CartItemService {
	
	public ResponseEntity<String> addToCartItem(String bookId);

}
