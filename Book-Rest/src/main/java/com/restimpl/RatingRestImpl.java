package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rest.RatingRest;
import com.service.RatingService;


@RestController
public class RatingRestImpl implements RatingRest {
	
	@Autowired
	private RatingService ratingService;

	@Override
	public ResponseEntity<String> addRating(Map<String, String> map) {
		try {
			return ratingService.addRating(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
