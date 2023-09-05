package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Cart;

@RequestMapping("/cart")
public interface CartRest {
	
	@PostMapping("/add")
	public ResponseEntity<String> addToCart(@RequestBody(required = true)Map<String, List<Integer>>map);
	
	@GetMapping("/all")
	public ResponseEntity<List<Cart>> getAllCart();
	
}
