package com.service;


import org.springframework.http.ResponseEntity;

/**
 * The CartService interface defines methods for managing a user's shopping cart, including adding items to the cart.
 */
public interface CartService {
	
	
	/**
     * Adds an item to the user's shopping cart.
     *
     * @return ResponseEntity with a success message if the item is successfully added to the cart (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails.
     */
	public ResponseEntity<String> addToCart();
	
}
