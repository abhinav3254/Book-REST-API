package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.CartDao;
import com.dao.OrdersDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Book;
import com.pojo.Cart;
import com.pojo.Orders;
import com.pojo.User;
import com.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private OrdersDao ordersDao;
	
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public ResponseEntity<String> placeOrder(Map<String, String> map) {
		try {
			Orders orders = orderConfig(map);
			ordersDao.save(orders);
			
			Cart cart = cartDao.getById(Integer.parseInt(map.get("cartId")));
			cartDao.deleteCartBooksByCartId(cart.getId().toString());
			
			// finding current user 
			Authentication authentication = SecurityContextHolder. getContext(). getAuthentication();
			String token = authentication.getName();
			String username = jwtUtils.extractUsername(token);
			User user = userDao.getUserByUserName(username);
			cartDao.deleteCartByUserId(user.getId().toString());
			return new ResponseEntity<String>(Constants.designMessage("Order Placed"),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private Orders orderConfig(Map<String, String>map) {
		try {
			Orders orders = new Orders();
			
			Cart cart = cartDao.getById(Integer.parseInt(map.get("cartId")));
			
			Authentication authentication = SecurityContextHolder. getContext(). getAuthentication();
			String token = authentication.getName();
			
			String username = jwtUtils.extractUsername(token);
			
			User user = userDao.getUserByUserName(username);
			
			List<Book> orderBooks = new ArrayList<>(cart.getBooks());
			
			orders.setBooks(orderBooks);
			orders.setUser(user);
			System.out.println(" -- >> abhinav"+orders.getBooks().toString());
			
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException(Constants.designMessage("ALL FIELDS ARE MANDATORY!!"));
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
