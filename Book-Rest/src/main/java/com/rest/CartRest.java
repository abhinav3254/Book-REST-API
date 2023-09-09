package com.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/cart")
public interface CartRest {
	
	@PostMapping("/add")
	public ResponseEntity<String> addToCart();

}
