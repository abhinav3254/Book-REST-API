package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pojo.CartItem;

public interface CartItemService {
	
	public ResponseEntity<String> addToCartItem(String bookId);
	
	public ResponseEntity<List<CartItem>> getAllItemsFromCart();
	
	public ResponseEntity<String> incrementItem(String cartItemId);

	public ResponseEntity<String> decrementItem(String cartItemId);
	
	public ResponseEntity<String> deleteItem(String cartItemId);
	
	public ResponseEntity<Double> getSumOfAllFinalPrice();

}
