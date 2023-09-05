package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Author;

@RequestMapping("/author")
public interface AuthorRest {
	
	@PostMapping("/add")
	public ResponseEntity<String> addAuthor(@RequestBody(required = true)Map<String, String>map);
	
	@GetMapping("/all")
	ResponseEntity<List<Author>> getAllAuthor();
}
