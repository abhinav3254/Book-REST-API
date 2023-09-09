package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rest.PaymentRest;
import com.service.PaymentService;

@RestController
public class PaymentRestImpl implements PaymentRest {
	
	@Autowired
	private PaymentService paymentService;

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
