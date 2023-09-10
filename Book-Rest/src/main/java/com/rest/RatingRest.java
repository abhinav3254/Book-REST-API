package com.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/rating")
public interface RatingRest {
	
	@PostMapping("/add")
	public ResponseEntity<String> addRating(@RequestBody(required = true)Map<String, String> map);
	

}
