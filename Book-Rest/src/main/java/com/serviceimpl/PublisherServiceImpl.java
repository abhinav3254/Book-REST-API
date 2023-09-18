package com.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.PublishersDao;
import com.jwt.JwtFilter;
import com.pojo.Publishers;
import com.service.PublisherService;

/**
 * This class provides implementations for managing publisher-related operations in the system.
 * It includes methods for adding publishers, retrieving a list of all publishers, and configuring Publishers objects.
 * The {@code @Service} annotation is used to indicate that this class is a Spring-managed service component.
 * Spring will automatically detect and create a bean of this class during component scanning.
 * 
 */ 
@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublishersDao dao;

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Adds a new publisher to the system, accessible only to administrators.
     *
     * @parameter map A map containing properties to configure the new publisher.
     * @return ResponseEntity with a success message if the publisher is added (HTTP status OK),
     *         or an "UNAUTHORIZED ACCESS" message with an unauthorized status if the user is not an admin,
     *         or an error message with an internal server error status if an exception occurs.
     */
    @Override
    public ResponseEntity<String> addPublisher(Map<String, String> map) {
        try {
            // Check if the user making the request is an admin using the jwtFilter.
            if (jwtFilter.isAdmin()) {
                // Configure and create a new Publishers object based on the provided map.
                Publishers publishers = configPublishers(map);
                
                // Save the new publisher to the data store.
                dao.save(publishers);
                
                // Return a success response with a message indicating the publisher was added.
                return new ResponseEntity<String>(Constants.designMessage("Added Publisher !!"), HttpStatus.OK);
            } else {
                // If the user is not an admin, return an "UNAUTHORIZED ACCESS" response with an unauthorized status.
                return new ResponseEntity<String>(Constants.designMessage("UNAUTHORIZED ACCESS"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            // If an exception occurs during the process, log the error for debugging.
            System.out.println("Error in PublisherServiceImpl class: " + e);
        }
        
        // If an exception occurred during the process, return an error response with an internal server error status.
        return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Configures and creates a Publishers object based on a provided map of properties.
     *
     * @parameter map A map containing properties to configure the Publishers object.
     * @return A Publishers object with properties set as specified in the map.
     * @throws RuntimeException if any exception occurs during the configuration process.
     */
    private Publishers configPublishers(Map<String, String> map) {
        try {
            // Create a new Publishers object.
            Publishers publishers = new Publishers();
            
            // Set the publisher's name property using the "name" value from the map.
            publishers.setPublisherName(map.get("name"));
            
            // Set the publisher's country property (Note: "country" property should be fetched from the map).
            publishers.setCountry("country");
            
            // Return the configured Publishers object.
            return publishers;
        } catch (Exception e) {
            // If an exception occurs during configuration, log the error for debugging.
            System.out.println("Error in PublisherServiceImpl class: " + e);
        }
        
        // If an exception occurred during configuration, throw a runtime exception.
        throw new RuntimeException("Error in PublisherServiceImpl");
    }

    /**
     * Retrieves a list of all publishers from the data source.
     *
     * @return ResponseEntity containing a list of Publishers if successful (HTTP status OK),
     *         or an empty list with an internal server error status if an exception occurs.
     */
    @Override
    public ResponseEntity<List<Publishers>> getAllPublishers() {
        try {
            // Attempt to retrieve a list of publishers from the data access object (DAO).
            List<Publishers> listPublishers = dao.findAll();
            
            // If the retrieval is successful, return the list of publishers with an HTTP status of OK.
            return new ResponseEntity<List<Publishers>>(listPublishers, HttpStatus.OK);
        } catch (Exception e) {
            // If an exception occurs during the retrieval process, print the stack trace for debugging.
            e.printStackTrace();
        }
        
        // If an exception occurred, return an empty list of publishers with an internal server error status.
        return new ResponseEntity<List<Publishers>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
