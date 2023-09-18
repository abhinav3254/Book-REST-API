package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Author;

/**
 * The AuthorRest interface defines REST endpoints for managing authors in the system.
 * Users can use these endpoints to add new authors and retrieve a list of all authors.
 */
@RequestMapping("/author")
public interface AuthorRest {
	
	/**
     * Adds a new author to the system based on the provided author details.
     *
     * @param map A map containing author information.
     * @return ResponseEntity with a success message if the author is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@PostMapping("/add")
	public ResponseEntity<String> addAuthor(@RequestBody(required = true)Map<String, String>map);
	
	
	/**
     * Retrieves a list of all authors in the system.
     *
     * @return ResponseEntity with a list of authors if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/all")
	ResponseEntity<List<Author>> getAllAuthor();
}
