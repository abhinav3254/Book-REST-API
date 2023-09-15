package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Orders;

@RequestMapping("/orders")
public interface OrdersRest {
	
	@PostMapping("/order")
	public ResponseEntity<String> placeOrder(@RequestBody(required = true)Map<String, String>map);
	
	@GetMapping("/all")
	public ResponseEntity<List<Orders>> getAllOrder();
	
	@GetMapping("/show/all")
	public ResponseEntity<List<Orders>> getAllTheOrdersPlaced();
	
	@GetMapping("/refund")
	public ResponseEntity<List<Orders>> getAllRefund();
	
}
