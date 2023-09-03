package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Cart;

public interface CartService {
	
	public ResponseEntity<String> addToCart(Map<String, List<Integer>>map);
	
	public ResponseEntity<List<Cart>> getAllCart();
}
