package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface UserService {
	
	ResponseEntity<String> signUp(Map<String, String> requestMap);
	
	ResponseEntity<String> logIn(Map<String, String> requestMap);

	ResponseEntity<String> isAdminCheck();
	
}
