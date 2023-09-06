package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Book;
import com.pojo.Cart;

public interface CartService {
	
	public ResponseEntity<String> addToCart(List<Integer> list);
	
	public ResponseEntity<List<Cart>> getAllCart();
	
	public ResponseEntity<List<Book>> getFromCart(List<Integer> list);
	
}
