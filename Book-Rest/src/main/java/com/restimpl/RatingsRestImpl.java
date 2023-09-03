package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.constants.Constants;
import com.rest.RatingsRest;
import com.service.RatingsService;

public class RatingsRestImpl implements RatingsRest {
	
	@Autowired
	private RatingsService ratingsService;

	@Override
	public ResponseEntity<String> postRating(Map<String, String> map) {
		try {
			return ratingsService.postRating(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG"),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
