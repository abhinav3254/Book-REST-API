package com.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * The RatingsRest interface defines the REST endpoint for adding ratings in the system.
 * Users can provide ratings for various items using this endpoint.
 */
@RequestMapping("/ratings")
public interface RatingsRest {
	
	
	/**
     * Adds a rating for a specific item in the system based on the provided data.
     *
     * @param map A map containing the data required for adding the rating.
     * @return ResponseEntity with a success message if the rating is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@PostMapping("/add")
	public ResponseEntity<String> addRating(@RequestBody(required = true)Map<String, String>map);

}
