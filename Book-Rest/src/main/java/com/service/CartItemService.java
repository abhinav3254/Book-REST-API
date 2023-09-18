package com.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pojo.CartItem;


/**
 * The CartItemService interface defines methods for managing items in a user's shopping cart, such as adding, updating,
 * and removing items, as well as retrieving cart information.
 */
public interface CartItemService {
	
	
	/**
     * Adds a book item to the user's shopping cart.
     *
     * @param bookId The ID of the book to be added to the cart.
     * @return ResponseEntity with a success message if the item is successfully added to the cart (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails.
     */
	public ResponseEntity<String> addToCartItem(String bookId);
	
	
	/**
     * Retrieves a list of all items in the user's shopping cart.
     *
     * @return ResponseEntity containing a list of cart items if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	public ResponseEntity<List<CartItem>> getAllItemsFromCart();
	
	
	/**
     * Increments the quantity of a cart item.
     *
     * @param cartItemId The ID of the cart item to increment.
     * @return ResponseEntity with a success message if the quantity is successfully incremented (HTTP status OK),
     *         or an error response with an appropriate status code if the increment fails.
     */
	public ResponseEntity<String> incrementItem(String cartItemId);

	
	/**
     * Decrements the quantity of a cart item.
     *
     * @param cartItemId The ID of the cart item to decrement.
     * @return ResponseEntity with a success message if the quantity is successfully decremented (HTTP status OK),
     *         or an error response with an appropriate status code if the decrement fails.
     */
	public ResponseEntity<String> decrementItem(String cartItemId);
	
	
	/**
     * Deletes a cart item from the user's shopping cart.
     *
     * @param cartItemId The ID of the cart item to be deleted.
     * @return ResponseEntity with a success message if the item is successfully deleted from the cart (HTTP status OK),
     *         or an error response with an appropriate status code if the deletion fails.
     */
	public ResponseEntity<String> deleteItem(String cartItemId);
	
	/**
     * Calculates and retrieves the sum of all final prices of items in the user's shopping cart.
     *
     * @return ResponseEntity containing the sum of final prices if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	public ResponseEntity<Double> getSumOfAllFinalPrice();
	
	
	/**
     * Deletes all items from the user's shopping cart.
     *
     * @return ResponseEntity with a success message if all items are successfully deleted from the cart (HTTP status OK),
     *         or an error response with an appropriate status code if the deletion fails.
     */
	public ResponseEntity<String> deleteAll();

}
