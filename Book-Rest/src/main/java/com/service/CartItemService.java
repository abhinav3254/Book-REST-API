package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pojo.CartItem;

public interface CartItemService {
	
	public ResponseEntity<String> addToCartItem(String bookId);
	
	public ResponseEntity<List<CartItem>> getAllItemsFromCart();

}
