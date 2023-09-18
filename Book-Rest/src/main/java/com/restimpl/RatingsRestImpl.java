package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rest.RatingsRest;
import com.service.RatingsService;


/**
 * The RatingsRestImpl class implements the RatingsRest interface and serves as the REST controller
 * for handling book ratings and reviews in the system. It provides an endpoint for users to add ratings
 * and reviews for books.
 */
@RestController
public class RatingsRestImpl implements RatingsRest {

	// Autowired RatingsService for handling rating-related operations
	@Autowired
	private RatingsService ratingsService;
	
	/**
     * Adds a new rating and review for a book based on the provided information.
     *
     * @param map A map containing rating and review details, including book ID, user ID, rating value, and review text.
     * @return ResponseEntity with a success message if the rating and review are added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> addRating(Map<String, String> map) {
		try {
			return ratingsService.addRating(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
