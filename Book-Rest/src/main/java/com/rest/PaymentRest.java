package com.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The PaymentRest interface defines REST endpoints for managing payment-related operations in the system.
 * Users can use these endpoints to save payment information.
 */
@RequestMapping("/payment")
public interface PaymentRest {
	
	
	/**
     * Saves payment information in the system based on the provided data.
     *
     * @param map A map containing payment information to be saved.
     * @return ResponseEntity with a success message if the payment information is saved successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the save operation fails or an exception occurs.
     */
	@PostMapping("/add")
	public ResponseEntity<String> savePayment(@RequestBody(required = true) Map<String, String>map);

}
