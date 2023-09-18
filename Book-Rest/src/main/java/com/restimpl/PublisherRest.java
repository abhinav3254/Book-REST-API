package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.pojo.Publishers;
import com.service.PublisherService;


/**
 * The PublisherRest class implements the PublisherRest interface and serves as the REST controller
 * for managing publishers in the system. It provides endpoints for adding new publishers and retrieving
 * a list of all publishers.
 */
@RestController
public class PublisherRest implements com.rest.PublisherRest {
	
	
	// Autowired PublisherService for handling publisher-related operations
	@Autowired
	private PublisherService publisherService;

	
	/**
     * Adds a new publisher to the system based on the provided information.
     *
     * @param map A map containing publisher details, including the publisher name, address, and contact information.
     * @return ResponseEntity with a success message if the publisher is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> addPublisher(Map<String, String> map) {
		
		try {
			return publisherService.addPublisher(map);
		} catch (Exception e) {
			System.out.println("Error in PublisherRest class :- "+e);
		}
		
		return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG"),HttpStatus.OK);
	}

	
	/**
     * Retrieves a list of all publishers in the system.
     *
     * @return ResponseEntity with a list of publishers if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Publishers>> getAllPublishers() {
		try {
			return publisherService.getAllPublishers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Publishers>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
