package com.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.User;

@RequestMapping("/user")
public interface UserRest {
	
	@PostMapping("/signup")
	ResponseEntity<String> signUp(@RequestBody(required = true)Map<String, String> requestMap);
	
	@PostMapping("/login")
	ResponseEntity<String> logIn(@RequestBody(required = true)Map<String, String> requestMap);
	
	@GetMapping("/isAdmin")
	ResponseEntity<String> isAdminCheck();
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUser();
	
	@PostMapping("/updateProfile")
	public ResponseEntity<String> updateProfile(@RequestBody(required = true)Map<String, String>map);
}
