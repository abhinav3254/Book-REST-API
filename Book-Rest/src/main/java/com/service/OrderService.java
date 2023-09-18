package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Orders;


/**
 * The OrderService interface defines methods for managing orders, including placing orders,
 * retrieving orders, and handling order-related operations.
 */
public interface OrderService {
	
	
	/**
     * Places an order based on the provided map of order details.
     *
     * @param map A map containing order information, such as products, quantities, and customer details.
     * @return ResponseEntity with a success message if the order is successfully placed (HTTP status OK),
     *         or an error response with an appropriate status code if the order placement fails.
     */
	public ResponseEntity<String> placeOrder(Map<String, String>map);
	
	
	/**
     * Retrieves a list of all orders in the system.
     *
     * @return ResponseEntity with a list of orders if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	public ResponseEntity<List<Orders>> getAllOrders();
	
	
	/**
     * Retrieves a list of all the orders that have been placed.
     *
     * @return ResponseEntity with a list of placed orders if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	public ResponseEntity<List<Orders>> getAllTheOrdersPlaced();
	
	
	/**
     * Retrieves a list of all refund requests related to orders.
     *
     * @return ResponseEntity with a list of refund requests if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	public ResponseEntity<List<Orders>>getAllRefund();
}
