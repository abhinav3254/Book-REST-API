package com.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/publisher")
public interface PublisherRest {

	@PostMapping("/add")
	public ResponseEntity<String> addPublisher(@RequestBody(required = true)Map<String, String> map);
	
}
