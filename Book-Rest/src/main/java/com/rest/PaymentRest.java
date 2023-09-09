package com.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/payment")
public interface PaymentRest {
	
	@PostMapping("/add")
	public ResponseEntity<String> savePayment(@RequestBody(required = true) Map<String, String>map);

}
