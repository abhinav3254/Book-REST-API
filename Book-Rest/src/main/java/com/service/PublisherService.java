package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Publishers;

public interface PublisherService {
	
	public ResponseEntity<String> addPublisher(Map<String, String>map);
	
	public ResponseEntity<List<Publishers>> getAllPublishers();
	
}
