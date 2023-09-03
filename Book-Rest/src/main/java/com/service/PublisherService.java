package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface PublisherService {
	
	public ResponseEntity<String> addPublisher(Map<String, String>map);
	
}
