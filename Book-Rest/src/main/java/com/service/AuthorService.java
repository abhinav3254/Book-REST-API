package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Author;

public interface AuthorService {
	
	public ResponseEntity<String> addAuthor(Map<String, String> map);
	
	public ResponseEntity<List<Author>> getAllAuthor();

}
