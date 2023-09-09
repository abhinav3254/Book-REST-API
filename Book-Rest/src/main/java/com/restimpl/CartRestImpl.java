package com.restimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.rest.CartRest;
import com.service.CartService;

@RestController
public class CartRestImpl implements CartRest {

	
	@Autowired
	private CartService cartService;
	
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
