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

/**
 * The OrderRestImpl class implements the OrdersRest interface and serves as the REST controller
 * for handling order-related operations in the system. It provides endpoints for placing orders,
 * retrieving all orders, retrieving all placed orders, and retrieving all refund requests.
 */
@RestController
public class OrderRestImpl implements OrdersRest {
	
	// Autowired OrderService for handling order-related operations
	@Autowired
	private OrderService orderService;

	/**
     * Places a new order in the system based on the provided order details.
     *
     * @param map A map containing order information, including user details, book details, and payment information.
     * @return ResponseEntity with a success message if the order is placed successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the placement fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> placeOrder(Map<String, String>map) {
		try {
			return orderService.placeOrder(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Retrieves a list of all orders in the system.
     *
     * @return ResponseEntity with a list of orders if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Orders>> getAllOrder() {
		try {
			return orderService.getAllOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Retrieves a list of all placed orders in the system.
     *
     * @return ResponseEntity with a list of placed orders if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Orders>> getAllTheOrdersPlaced() {
		try {
			return orderService.getAllTheOrdersPlaced();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Retrieves a list of all refund requests in the system.
     *
     * @return ResponseEntity with a list of refund requests if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Orders>> getAllRefund() {
		try {
			return orderService.getAllRefund();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Orders>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
