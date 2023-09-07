package com.serviceimpl;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.BookDao;
import com.dao.CartDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Book;
import com.pojo.Cart;
import com.pojo.User;
import com.service.CartService;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<String> addToCart(List<Integer> list) {
		try {
			List<Integer> listBookId = list;
			List<Book> listBook = new ArrayList<Book>();
			
			System.out.println(list.toString());
			
			listBook = bookDao.findAllById(listBookId);
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();;
			String token = auth.getName();

			String username = jwtUtils.extractUsername(token);

			User user = userDao.getUserByUserName(username);

			Cart cart = new Cart();
			cart.setBooks(listBook);

			cart.setUser(user);

			cartDao.save(cart);
			
			return new ResponseEntity<String>(Constants.designMessage("ADDED"), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Cart>> getAllCart() {
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();;
			String token = auth.getName();

			String username = jwtUtils.extractUsername(token);

			User user = userDao.getUserByUserName(username);
			
			System.out.println(user.getName());
			System.out.println(user.getId());

			List<Cart> listBooks = cartDao.getAllItemsInCart(user.getId().toString());
			return new ResponseEntity<List<Cart>>(listBooks, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Cart>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> getFromCart(List<Integer> list) {
		try {
			List<Integer> listBookId = list;
			List<Book> listBook = new ArrayList<Book>();
			
			System.out.println(list.toString());
			
			listBook = bookDao.findAllById(listBookId);
			
			return new ResponseEntity<List<Book>>(listBook,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
