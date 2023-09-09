package com.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.Constants;
import com.dao.BookDao;
import com.dao.CartDao;
import com.dao.CartItemDao;
import com.dao.OrdersDao;
import com.dao.PaymentDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Book;
import com.pojo.Cart;
import com.pojo.CartItem;
import com.pojo.Orders;
import com.pojo.Payment;
import com.pojo.User;
import com.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private CartDao cartDao;
	
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private CartServiceImpl cartServiceImpl;

	@Override
	public ResponseEntity<String> placeOrder(Map<String, String>map) {
	    try {
	    	
	    	// first add items in the cart then place order
	    	cartServiceImpl.addToCart();
	    	
	        // Assuming you can obtain the currently authenticated user
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userToken = authentication.getName();
	        String username = jwtUtils.extractUsername(userToken);
	        User user = userDao.getUserByUserName(username);
	        
	        Cart cart = cartDao.getCartByUserId(user.getId());
	        
	        Payment payment = paymentDao.getAllPaymentByUser(user.getId());
	        
	        String address = map.get("address");
	        
	        Orders orders = new Orders();
	        
	        orders.setOrderDate(LocalDateTime.now());
	        
	        orders.setCart(cart);
	        orders.setUser(user);
	        orders.setAddress(address);
	        orders.setPayment(payment);
	        
	        
	        ordersDao.save(orders);
	        
	        
	     // Get the user ID
	        Integer userId = user.getId();

	        // Retrieve the payment and cart items for the user
	        Payment payment2 = paymentDao.getAllPaymentByUser(userId);
	        List<CartItem> cartItems = cartItemDao.getAllItemsFromCart(userId);

	        // Set user details to null for payment
	        if (payment2 != null) {
	            payment2.setUser(null);
	            paymentDao.save(payment2); // Update the payment in the database
	        }

	        // Set user details to null for each cart item
	        for (CartItem cartItem : cartItems) {
	            cartItem.setUser(null);
	            cartItemDao.save(cartItem); // Update each cart item in the database
	        }

	        return new ResponseEntity<String>("ORDER PLACED", HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<String>("Error placing order", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	@Override
	public ResponseEntity<List<Orders>> getAllOrders() {
		try {
			Authentication authentication = SecurityContextHolder. getContext(). getAuthentication();
			String token = authentication.getName();
			
			String username = jwtUtils.extractUsername(token);
			
			User user = userDao.getUserByUserName(username);
			
			List<Orders> listOrders = ordersDao.getAllOrders(user.getId().toString());

			return new ResponseEntity<List<Orders>>(listOrders,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
