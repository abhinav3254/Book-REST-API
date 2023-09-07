package com.service;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;

import com.pojo.Orders;

public interface OrderService {
	
	public ResponseEntity<String> placeOrder(List<Integer> list);
	
	public ResponseEntity<List<Orders>> getAllOrders();
}
