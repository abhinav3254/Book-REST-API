package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rest.PaymentRest;
import com.service.PaymentService;


/**
 * The PaymentRestImpl class implements the PaymentRest interface and serves as the REST controller
 * for handling payment-related operations in the system. It provides an endpoint for saving payment information.
 */
@RestController
public class PaymentRestImpl implements PaymentRest {
	
	// Autowired PaymentService for handling payment-related operations
	@Autowired
	private PaymentService paymentService;

	
	/**
     * Saves payment information to the system based on the provided details.
     *
     * @param map A map containing payment information, including the payment method, amount, and transaction details.
     * @return ResponseEntity with a success message if the payment is saved successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the saving fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> savePayment(Map<String, String> map) {
		try {
			return paymentService.savePayment(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
