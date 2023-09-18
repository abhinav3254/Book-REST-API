package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Author;


/**
 * The AuthorService interface defines methods for managing author-related operations in the system.
 * It provides functionality to add authors and retrieve a list of all authors.
 */
public interface AuthorService {
	
	/**
	 * Retrieves a list of all authors in the system.
	 *
	 * @return ResponseEntity with a list of authors if the request is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if an exception occurs.
	 */
	public ResponseEntity<String> addAuthor(Map<String, String> map);
	
	/**
	 * Retrieves a list of all authors in the system.
	 *
	 * @return ResponseEntity with a list of authors if the request is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if an exception occurs.
	 */
	public ResponseEntity<List<Author>> getAllAuthor();

}
