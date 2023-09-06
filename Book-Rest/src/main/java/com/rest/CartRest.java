package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Book;
import com.pojo.Cart;

@RequestMapping("/cart")
public interface CartRest {
	
	@PostMapping("/add")
	public ResponseEntity<String> addToCart(@RequestBody(required = true)List<Integer> list);
	
	@GetMapping("/all")
	public ResponseEntity<List<Cart>> getAllCart();
	
	@PostMapping("/all2")
	public ResponseEntity<List<Book>> getFromCart(@RequestBody(required = true)List<Integer> list);
	
}
