package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface PaymentService {
	
	public ResponseEntity<String> savePayment(Map<String, String>map);

}
