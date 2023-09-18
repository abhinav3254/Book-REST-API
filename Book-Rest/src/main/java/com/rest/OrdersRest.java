package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Orders;

/**
 * The OrdersRest interface defines REST endpoints for managing orders in the system.
 * Users can use these endpoints to place orders, retrieve all orders, retrieve all placed orders,
 * and retrieve all refund orders.
 */
@RequestMapping("/orders")
public interface OrdersRest {
	
	/**
     * Places an order in the system based on the provided order data.
     *
     * @param map A map containing order information to be placed.
     * @return ResponseEntity with a success message if the order is placed successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the placement fails or an exception occurs.
     */
	@PostMapping("/order")
	public ResponseEntity<String> placeOrder(@RequestBody(required = true)Map<String, String>map);
	
	
	/**
     * Retrieves a list of all orders in the system.
     *
     * @return ResponseEntity with a list of orders if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/all")
	public ResponseEntity<List<Orders>> getAllOrder();
	
	
	/**
     * Retrieves a list of all orders that have been placed in the system.
     *
     * @return ResponseEntity with a list of placed orders if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/show/all")
	public ResponseEntity<List<Orders>> getAllTheOrdersPlaced();
	
	
	/**
     * Retrieves a list of all refund orders in the system.
     *
     * @return ResponseEntity with a list of refund orders if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/refund")
	public ResponseEntity<List<Orders>> getAllRefund();
	
}
