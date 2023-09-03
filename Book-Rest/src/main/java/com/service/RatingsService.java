package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface RatingsService {

	public ResponseEntity<String> postRating(Map<String, String>map);
	
}
