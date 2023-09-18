package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Publishers;

/**
 * The PublisherRest interface defines REST endpoints for managing publishers in the system.
 * Users can add new publishers and retrieve a list of all publishers using these endpoints.
 */
@RequestMapping("/publisher")
public interface PublisherRest {

	
	/**
     * Adds a new publisher to the system based on the provided data.
     *
     * @param map A map containing the data required for adding the publisher.
     * @return ResponseEntity with a success message if the publisher is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@PostMapping("/add")
	public ResponseEntity<String> addPublisher(@RequestBody(required = true)Map<String, String> map);
	
	
	/**
     * Retrieves a list of all publishers available in the system.
     *
     * @return ResponseEntity with a list of publishers if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/all")
	public ResponseEntity<List<Publishers>> getAllPublishers();
	
}
