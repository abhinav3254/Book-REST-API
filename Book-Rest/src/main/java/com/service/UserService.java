package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.User;

public interface UserService {
	
	ResponseEntity<String> signUp(Map<String, String> requestMap);
	
	ResponseEntity<String> logIn(Map<String, String> requestMap);

	ResponseEntity<String> isAdminCheck();
	
	ResponseEntity<User> getProfile();
	
	public ResponseEntity<String> updateProfile(Map<String, String>map);
	
}
