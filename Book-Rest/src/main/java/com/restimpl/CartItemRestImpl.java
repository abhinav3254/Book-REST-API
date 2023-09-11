package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.CartItem;
import com.rest.CartItemRest;
import com.service.CartItemService;


@RestController
public class CartItemRestImpl implements CartItemRest {
	
	@Autowired
	private CartItemService cartItemService;

	@Override
	public ResponseEntity<String> addToCartItem(String bookId) {
		try {
			return cartItemService.addToCartItem(bookId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<CartItem>> getAllItemsFromCart() {
		try {
			return cartItemService.getAllItemsFromCart();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<CartItem>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> incrementItem(String cartItemId) {
		try {
			return cartItemService.incrementItem(cartItemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> decrementItem(String cartItemId) {
		try {
			return cartItemService.decrementItem(cartItemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	Delete Item from the cart Item
	@Override
	public ResponseEntity<String> deleteItem(String cartItemId) {
		try {
			return cartItemService.deleteItem(cartItemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Double> getSumOfAllFinalPrice() {
		try {
			return cartItemService.getSumOfAllFinalPrice();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Double>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteAll() {
		try {
			return cartItemService.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
