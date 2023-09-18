package com.restimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.User;
import com.rest.UserRest;
import com.service.UserService;


/**
 * The UserRestImpl class implements the UserRest interface and serves as the REST controller
 * for handling user-related operations in the system. It provides endpoints for user registration,
 * login, checking admin privileges, retrieving user profiles, and updating user profiles.
 */
@RestController
public class UserRestImpl implements UserRest {

	// Autowired UserService for handling user-related operations
	@Autowired
	private UserService userService;

	
	/**
     * Registers a new user in the system based on the provided user details.
     *
     * @param requestMap A map containing user registration information.
     * @return ResponseEntity with a success message if user registration is successful (HTTP status OK),
     *         or an error response with an appropriate status code if registration fails.
     */
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {

			return userService.signUp(requestMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Authenticates a user based on the provided login credentials.
     *
     * @param requestMap A map containing user login credentials such as username and password.
     * @return ResponseEntity with a success message if login is successful (HTTP status OK),
     *         or an error response with an appropriate status code if authentication fails.
     */
	@Override
	public ResponseEntity<String> logIn(Map<String, String> requestMap) {
		try {

			return userService.logIn(requestMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Checks if the currently authenticated user has administrative privileges.
     *
     * @return ResponseEntity with a success message if the user has admin privileges (HTTP status OK),
     *         or an error response with an appropriate status code if the user is not an admin or an exception occurs.
     */
	@Override
	public ResponseEntity<String> isAdminCheck() {
		try {
			return userService.isAdminCheck();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("INTERNAL SERVER ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Retrieves the user profile information of the currently authenticated user.
     *
     * @return ResponseEntity with the user's profile data if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs or the user is not authenticated.
     */
	@Override
	public ResponseEntity<User> getUser() {
		try {
			return userService.getProfile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Updates the profile information of the currently authenticated user based on the provided map of user data.
     *
     * @param map A map containing updated user profile information.
     * @return ResponseEntity with a success message if the profile is successfully updated (HTTP status OK),
     *         or an error response with an appropriate status code if the update fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> updateProfile(Map<String, String> map) {
		try {
			return userService.updateProfile(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
