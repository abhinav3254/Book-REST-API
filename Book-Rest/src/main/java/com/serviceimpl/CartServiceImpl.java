package com.serviceimpl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.CartDao;
import com.dao.CartItemDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Cart;
import com.pojo.CartItem;
import com.pojo.User;
import com.service.CartService;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	
	/**
	 * Adds items to the cart for the currently authenticated user.
	 * This method is for testing and checking purposes.
	 *
	 * @return ResponseEntity with a success message if the items are added to the cart (HTTP status OK),
	 *         or an error message with an internal server error status if an exception occurs.
	 *         
	 * NOTE :- This Method is FOR TESTING PURPOSE DON"T REMOVE IT
	 * DATED :- 12/09/2023
	 */
	@Override
	public ResponseEntity<String> addToCart() {
		try {
			
			System.out.println("Adding elements to the cart");
			// to get the currently logged in user
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();;
			String token = auth.getName();
			String username = jwtUtils.extractUsername(token);
			User user = userDao.getUserByUserName(username);
			
			
			// finding all the cart items of a particular user
			List<CartItem> listCartItems = cartItemDao.getAllItemsFromCart(user.getId());
			
			
			// check the quantity here if user has added more items in the cart or not
			// Manage the quantity of the book quantity here before adding it to the cart
			// checking every item quantity
			for(int i = 0 ;i<listCartItems.size();i++) {
				int backendQuantity = listCartItems.get(i).getBook().getBookQuantity();
				int frontEndQuantity = listCartItems.get(i).getQuantity();
				System.out.println("From Backend :- "+listCartItems.get(i).getBook().getBookQuantity());
				System.out.println("From frontend :- "+listCartItems.get(i).getQuantity());
				if (backendQuantity<frontEndQuantity) {
					return new ResponseEntity<String>("Can't Place Order",HttpStatus.BAD_REQUEST);
				} else {
					if (listCartItems.get(i).getBook().getBookQuantity()>=listCartItems.get(i).getQuantity()) {
						// do nothing
					} else {
						return new ResponseEntity<String>(listCartItems.get(i).getBook().getTitle()+" this book quantity issue \n Book can't be ordered..",HttpStatus.BAD_REQUEST);
					}
				}
			}
			
			Cart cart = new Cart();
			
			cart.setCartItems(listCartItems);
			cart.setUser(user);
			
			cartDao.save(cart);
			
			System.out.println("Removing reference of userid");
			// removing the reference of user so that it can't display every time
			for (int i = 0; i < listCartItems.size(); i++) {
			    CartItem cartItem = listCartItems.get(i);
			    cartItem.setUser(null); // Set the user to null
			    cartItemDao.save(cartItem); // Update the cart item in the database
			}
			
			System.out.println("Added to Cart Implementation done");
			return new ResponseEntity<String>(Constants.designMessage("ADDED"), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**
	 * Adds all items to the cart for the currently authenticated user.
	 * This method manages the quantity of cart_items and checks if items can be added to the cart.
	 *
	 * @return True if all items are successfully added to the cart, false if any quantity check fails.
	 */
	public Boolean addToCart2() {
		System.out.println("Adding elements to the cart");

		// Get the currently logged in user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String token = auth.getName();
		String username = jwtUtils.extractUsername(token);
		User user = userDao.getUserByUserName(username);

		// Find all the cart items of a particular user
		List<CartItem> listCartItems = cartItemDao.getAllItemsFromCart(user.getId());

		// Check the quantity here if the user has added more items in the cart than available
		boolean flag = true; // Initialize flag to true

		// Check the quantity for each item in the cart
		for (int i = 0; i < listCartItems.size(); i++) {
		    int backendQuantity = listCartItems.get(i).getBook().getBookQuantity();
		    int frontEndQuantity = listCartItems.get(i).getQuantity();
		    System.out.println("From Backend: " + backendQuantity);
		    System.out.println("From frontend: " + frontEndQuantity);

		    if (backendQuantity < frontEndQuantity) {
		        // If the quantity in the cart exceeds the available quantity, set flag to false and break the loop
		        flag = false;
		        break;
		    }
		}

		if (flag) {
		    // Create a new cart and save it
		    Cart cart = new Cart();
		    cart.setCartItems(listCartItems);
		    cart.setUser(user);
		    cartDao.save(cart);

		    System.out.println("Removing reference of userid");
		    
		    // Remove the reference of user so that it doesn't display every time
		    for (int i1 = 0; i1 < listCartItems.size(); i1++) {
		        CartItem cartItem = listCartItems.get(i1);
		        cartItem.setUser(null); // Set the user to null
		        cartItemDao.save(cartItem); // Update the cart item in the database
		    }
		    
		    System.out.println("Added to Cart Implementation done");
		    return true;
		} else {
		    // Quantity check failed, return false
		    return false;
		}

	}
	
}
