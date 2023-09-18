package com.restimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.rest.CartRest;
import com.service.CartService;

/**
 * The CartRestImpl class implements the CartRest interface and serves as the REST controller
 * for handling shopping cart-related operations in the system. It provides an endpoint for adding
 * items to the user's shopping cart.
 */
@RestController
public class CartRestImpl implements CartRest {

	// Autowired CartService for handling shopping cart-related operations
	@Autowired
	private CartService cartService;
	
	
	/**
     * Adds an item to the user's shopping cart in the system.
     *
     * @return ResponseEntity with a success message if the item is added to the cart successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> addToCart() {
		try {
			return cartService.addToCart();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL ISSUE"),HttpStatus.OK);
	}

}
