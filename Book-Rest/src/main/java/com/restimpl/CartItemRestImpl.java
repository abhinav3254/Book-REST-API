package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.CartItem;
import com.rest.CartItemRest;
import com.service.CartItemService;

/**
 * The CartItemRestImpl class implements the CartItemRest interface and serves as the REST controller
 * for handling shopping cart item-related operations in the system. It provides endpoints for adding,
 * updating, and deleting items from the user's shopping cart.
 */
@RestController
public class CartItemRestImpl implements CartItemRest {
	// Autowired CartItemService for handling shopping cart item-related operations
	@Autowired
	private CartItemService cartItemService;

	/**
     * Adds an item to the user's shopping cart in the system.
     *
     * @param bookId The unique identifier of the book to be added to the cart.
     * @return ResponseEntity with a success message if the item is added to the cart successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> addToCartItem(String bookId) {
		try {
			return cartItemService.addToCartItem(bookId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Retrieves a list of all items in the user's shopping cart.
     *
     * @return ResponseEntity with a list of cart items if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<CartItem>> getAllItemsFromCart() {
		try {
			return cartItemService.getAllItemsFromCart();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<CartItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Increments the quantity of a specific cart item in the user's shopping cart.
     *
     * @param cartItemId The unique identifier of the cart item to be incremented.
     * @return ResponseEntity with a success message if the item quantity is incremented successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the increment fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> incrementItem(String cartItemId) {
		try {
			return cartItemService.incrementItem(cartItemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Decrements the quantity of a specific cart item in the user's shopping cart.
     *
     * @param cartItemId The unique identifier of the cart item to be decremented.
     * @return ResponseEntity with a success message if the item quantity is decremented successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the decrement fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> decrementItem(String cartItemId) {
		try {
			return cartItemService.decrementItem(cartItemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Deletes a specific cart item from the user's shopping cart.
     *
     * @param cartItemId The unique identifier of the cart item to be deleted.
     * @return ResponseEntity with a success message if the item is deleted successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the deletion fails or an exception occurs.
     */
//	Delete Item from the cart Item
	@Override
	public ResponseEntity<String> deleteItem(String cartItemId) {
		try {
			return cartItemService.deleteItem(cartItemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Retrieves the sum of the final prices of all items in the user's shopping cart.
     *
     * @return ResponseEntity with the sum of final prices if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<Double> getSumOfAllFinalPrice() {
		try {
			return cartItemService.getSumOfAllFinalPrice();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Double>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Deletes all items from the user's shopping cart.
     *
     * @return ResponseEntity with a success message if all items are deleted successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the deletion fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> deleteAll() {
		try {
			return cartItemService.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
