package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;


/**
 * The RatingsService interface defines a method for adding ratings to a resource, typically used for user-generated ratings
 * or reviews.
 */
public interface RatingsService {
	
	
	/**
     * Adds a rating to a resource based on the provided map of rating information.
     *
     * @param map A map containing rating details, which may include the resource ID and the user's rating value.
     * @return ResponseEntity with a success message if the rating is successfully added (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails.
     */
	public ResponseEntity<String> addRating(Map<String, String> map);

}
