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
import com.dao.BookDao;
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
	private BookDao bookDao;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public ResponseEntity<String> placeOrder(List<Integer> list) {
		try {
			List<Integer> listBookId = list;
			List<Book> listBook = new ArrayList<Book>();	
			listBook = bookDao.findAllById(listBookId);
			
			Orders orders = new Orders();
			
			orders.setBooks(listBook);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userToken = authentication.getName();
			String username = jwtUtils.extractUsername(userToken);
			
			User user = userDao.getUserByUserName(username);
			
			orders.setUser(user);
			
			ordersDao.save(orders);
			
			return new ResponseEntity<String>("ORDER PLACED",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
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
