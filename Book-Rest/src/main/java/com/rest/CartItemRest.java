package com.rest;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.CartItem;

/**
 * The CartItemRest interface defines REST endpoints for managing cart items in the system.
 * Users can use these endpoints to add, retrieve, update, and delete items in their shopping carts.
 */
@RequestMapping("/cartItem")
public interface CartItemRest {
	
	
	/**
     * Adds a book to the user's shopping cart.
     *
     * @param bookId The unique identifier of the book to add to the cart.
     * @return ResponseEntity with a success message if the book is added to the cart successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@PostMapping("/add") 
	public ResponseEntity<String> addToCartItem(String bookId);
	
	/**
     * Retrieves all items from the user's shopping cart.
     *
     * @return ResponseEntity with a list of cart items if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/getAll")
	public ResponseEntity<List<CartItem>> getAllItemsFromCart();
	
	
	/**
     * Increases the quantity of a specific item in the cart.
     *
     * @param cartItemId The unique identifier of the cart item to increment.
     * @return ResponseEntity with a success message if the quantity is incremented successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the increment fails or an exception occurs.
     */
	@PostMapping("/incrementItem") 
	public ResponseEntity<String> incrementItem(String cartItemId);

	/**
     * Decreases the quantity of a specific item in the cart.
     *
     * @param cartItemId The unique identifier of the cart item to decrement.
     * @return ResponseEntity with a success message if the quantity is decremented successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the decrement fails or an exception occurs.
     */
//	Decrease the quantity in the cartItem
	@PostMapping("/decrementItem") 
	public ResponseEntity<String> decrementItem(String cartItemId);
	
	/**
     * Deletes a specific item from the user's shopping cart.
     *
     * @param cartItemId The unique identifier of the cart item to delete.
     * @return ResponseEntity with a success message if the item is deleted successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the deletion fails or an exception occurs.
     */
	// Delete Item from the cart
	@PostMapping("/deleteItem") 
	public ResponseEntity<String> deleteItem(String cartItemId);
	
	
	/**
     * Calculates and retrieves the sum of the final prices of all items in the cart.
     *
     * @return ResponseEntity with the sum of final prices if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/totalPriceSum")
	public ResponseEntity<Double> getSumOfAllFinalPrice();
	
	
	/**
     * Deletes all items from the user's shopping cart.
     *
     * @return ResponseEntity with a success message if all items are deleted successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the deletion fails or an exception occurs.
     */
	@GetMapping("/deleteAll")
	public ResponseEntity<String> deleteAll();

	
}
