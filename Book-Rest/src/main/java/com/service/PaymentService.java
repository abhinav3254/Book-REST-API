package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;


/**
 * The PaymentService interface defines methods for managing payments, such as saving payment information.
 */
public interface PaymentService {
	
	
	/**
     * Saves payment information to the system based on the provided map of payment details.
     *
     * @param map A map containing payment information, such as payment method, amount, and other relevant data.
     * @return ResponseEntity with a success message if the payment information is successfully saved (HTTP status OK),
     *         or an error response with an appropriate status code if the operation fails.
     */
	public ResponseEntity<String> savePayment(Map<String, String>map);

}
