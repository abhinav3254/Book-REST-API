package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.service.PublisherService;

@RestController
public class PublisherRest implements com.rest.PublisherRest {
	
	@Autowired
	private PublisherService publisherService;

	@Override
	public ResponseEntity<String> addPublisher(Map<String, String> map) {
		
		try {
			return publisherService.addPublisher(map);
		} catch (Exception e) {
			System.out.println("Error in PublisherRest class :- "+e);
		}
		
		return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG"),HttpStatus.OK);
	}

}
