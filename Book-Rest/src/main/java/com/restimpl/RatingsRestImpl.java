package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rest.RatingsRest;
import com.service.RatingsService;


@RestController
public class RatingsRestImpl implements RatingsRest {

	@Autowired
	private RatingsService ratingsService;
	
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
