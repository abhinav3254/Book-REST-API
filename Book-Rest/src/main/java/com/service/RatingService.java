package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface RatingService {
	
	public ResponseEntity<String> addRating(Map<String, String>map);

}
