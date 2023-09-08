package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Book;

public interface BookService {
	
	public ResponseEntity<String> addBook(Map<String, String>map);
	
	public ResponseEntity<List<Book>> getAllBook();
	
	public ResponseEntity<List<Book>> searchBooks(String search);
	
	public ResponseEntity<Book> getBookById(String id);
	
	public ResponseEntity<List<String>> getSuggestion();
	
	public ResponseEntity<List<Book>> getBookByCategory(String category);
	
}
