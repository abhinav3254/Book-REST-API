package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	
	@GetMapping("/search/{search}")
	public ResponseEntity<List<Book>> searchBooks(@PathVariable String search);
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable String id);
	
	@GetMapping("/suggest")
	public ResponseEntity<List<String>> getBookSuggestion();
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Book>> getBookByCategory(@PathVariable String category);
	
	@PostMapping("/updateQuantity")
	public ResponseEntity<String> updateQuantity(@RequestBody(required = true)Map<String, String>map);
	
	@GetMapping("/upcomingBooks")
	public ResponseEntity<List<Book>> getUpcomingBooks();
	
}
