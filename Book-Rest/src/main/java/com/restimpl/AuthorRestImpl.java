package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.pojo.Author;
import com.rest.AuthorRest;
import com.service.AuthorService;

/**
 * The AuthorRestImpl class implements the AuthorRest interface and serves as the REST controller
 * for handling author-related operations in the system. It provides endpoints for adding and retrieving authors.
 */
@RestController
public class AuthorRestImpl implements AuthorRest {
	
	// Autowired AuthorService for handling author-related operations
	@Autowired
	private AuthorService authorService;

	/**
     * Adds a new author to the system based on the provided author details.
     *
     * @param map A map containing author information.
     * @return ResponseEntity with a success message if the author is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> addAuthor(Map<String, String> map) {
		try {
			return authorService.addAuthor(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL SERVER ERROR!"),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Retrieves a list of all authors in the system.
     *
     * @return ResponseEntity with a list of authors if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Author>> getAllAuthor() {
		try {
			return authorService.getAllAuthor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Author>> (HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
