package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Publishers;


/**
 * The PublisherService interface defines methods for managing publishers, such as adding new publishers
 * and retrieving a list of all publishers.
 */
public interface PublisherService {
	
	
	/**
     * Adds a new publisher to the system based on the provided map of publisher information.
     *
     * @param map A map containing details of the publisher, such as name and other relevant data.
     * @return ResponseEntity with a success message if the publisher is successfully added (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails.
     */
	public ResponseEntity<String> addPublisher(Map<String, String>map);
	
	
	/**
     * Retrieves a list of all publishers in the system.
     *
     * @return ResponseEntity with a list of publishers if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	public ResponseEntity<List<Publishers>> getAllPublishers();
	
}
