package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.pojo.Book;
import com.pojo.Cart;
import com.rest.CartRest;
import com.service.CartService;

@RestController
public class CartRestImpl implements CartRest {

	
	@Autowired
	private CartService cartService;
	
	@Override
	public ResponseEntity<String> addToCart(List<Integer> list) {
		try {
			return cartService.addToCart(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL ISSUE"),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Cart>> getAllCart() {
		try {
			return cartService.getAllCart();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Cart>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> getFromCart(List<Integer> list) {
		try {
			return cartService.getFromCart(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
