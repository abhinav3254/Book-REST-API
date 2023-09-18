package com.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The CartRest interface defines a REST endpoint for managing user shopping carts in the system.
 * Users can use this endpoint to add items to their shopping carts.
 */
@RequestMapping("/cart")
public interface CartRest {
	
	/**
     * Adds an item to the user's shopping cart in the system.
     *
     * @return ResponseEntity with a success message if the item is added to the cart successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@PostMapping("/add")
	public ResponseEntity<String> addToCart();

}
