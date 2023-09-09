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
	private PaymentDao paymentDao;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public ResponseEntity<String> placeOrder(Map<String, String>map) {
	    try {
	    	
	        // Assuming you can obtain the currently authenticated user
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userToken = authentication.getName();
	        String username = jwtUtils.extractUsername(userToken);
	        User user = userDao.getUserByUserName(username);
	        
	        List<CartItem> listCartItems = cartItemDao.getAllItemsFromCart(user.getId());

	        Payment payment = paymentDao.getAllPaymentByUser(user.getId());
	        
	        String address = map.get("address");
	        
	        Orders orders = new Orders();
	        
	        orders.setOrderDate(LocalDateTime.now());
	        
	        orders.setUser(user);
	        orders.setAddress(address);
	        orders.setPayment(payment);
	        
	        orders.setCartItems(listCartItems);
	        
	        ordersDao.save(orders);
	        
	        Payment payment2 = paymentDao.getAllPaymentByUser(user.getId());
	        List<CartItem> cartItem = cartItemDao.getAllItemsFromCart(user.getId());
	        
	        payment2.setUser(null);
	        
	        for (int i = 0;i<cartItem.size();i++) {
	        	cartItem.get(i).setUser(null);
	        }
	        
	        /*
	        paymentDao.deleteById(payment2.getPid());
	        cartItemDao.deleteAllInBatch(cartItem);
	        */
	        

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
