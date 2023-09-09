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
	

	@Override
	public ResponseEntity<String> addToCart() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();;
			String token = auth.getName();
			String username = jwtUtils.extractUsername(token);
			User user = userDao.getUserByUserName(username);
			
			List<CartItem> listCartItems = cartItemDao.getAllItemsFromCart(user.getId());
			
			Cart cart = new Cart();
			
			cart.setCartItems(listCartItems);
			cart.setUser(user);
			
			cartDao.save(cart);
			
			// removing the reference of user so that it can't display every time
			for (int i = 0; i < listCartItems.size(); i++) {
			    CartItem cartItem = listCartItems.get(i);
			    cartItem.setUser(null); // Set the user to null
			    cartItemDao.save(cartItem); // Update the cart item in the database
			}
			
			return new ResponseEntity<String>(Constants.designMessage("ADDED"), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
