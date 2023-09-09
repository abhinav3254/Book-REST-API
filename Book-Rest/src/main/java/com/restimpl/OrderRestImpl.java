package com.restimpl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.pojo.Orders;
import com.rest.OrdersRest;
import com.service.OrderService;

@RestController
public class OrderRestImpl implements OrdersRest {
	
	@Autowired
	private OrderService orderService;

	@Override
	public ResponseEntity<String> placeOrder(Map<String, String>map) {
		try {
			return orderService.placeOrder(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Orders>> getAllOrder() {
		try {
			return orderService.getAllOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
