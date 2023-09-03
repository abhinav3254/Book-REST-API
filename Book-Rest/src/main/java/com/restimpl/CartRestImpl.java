package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.pojo.Cart;
import com.rest.CartRest;
import com.service.CartService;

@RestController
public class CartRestImpl implements CartRest {

	
	@Autowired
	private CartService cartService;
	
	@Override
	public ResponseEntity<String> addToCart(Map<String, List<Integer>> map) {
		try {
			return cartService.addToCart(map);
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

}
