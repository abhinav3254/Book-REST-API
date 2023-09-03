package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface AuthorService {
	
	public ResponseEntity<String> addAuthor(Map<String, String> map);

}
