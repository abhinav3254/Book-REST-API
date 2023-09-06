package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Book;

@RequestMapping("/book")
public interface BookRest {
	
	@PostMapping("/add")
	public ResponseEntity<String> addBook(@RequestBody(required = true)Map<String, String>map);
	
	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBooks();
	
	@PostMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestBody(required = true)Map<String, String>map);
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable String id);
	
}
