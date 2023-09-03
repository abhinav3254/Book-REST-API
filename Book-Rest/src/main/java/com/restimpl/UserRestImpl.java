package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.rest.UserRest;
import com.service.UserService;

@RestController
public class UserRestImpl implements UserRest {

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {

			return userService.signUp(requestMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> logIn(Map<String, String> requestMap) {
		try {

			return userService.logIn(requestMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
