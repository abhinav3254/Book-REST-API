package com.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.User;


/**
 * The UserService interface defines methods for user-related operations such as registration, authentication,
 * profile retrieval, and profile updating.
 */
public interface UserService {
	
	/**
	 * Registers a new user in the system based on the provided user details.
	 *
	 * @param requestMap A map containing user registration information.
	 * @return ResponseEntity with a success message if user registration is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if registration fails.
	 */
	public ResponseEntity<String> signUp(Map<String, String> requestMap);
	
	/**
	 * Authenticates a user based on the provided login credentials.
	 *
	 * @param requestMap A map containing user login credentials such as username and password.
	 * @return ResponseEntity with a success message if login is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if authentication fails.
	 */
	public ResponseEntity<String> logIn(Map<String, String> requestMap);

	
	/**
	 * Checks if the currently authenticated user has administrative privileges.
	 *
	 * @return ResponseEntity with a success message if the user has admin privileges (HTTP status OK),
	 *         or an error response with an appropriate status code if the user is not an admin or an exception occurs.
	 */
	public ResponseEntity<String> isAdminCheck();
	
	
	/**
	 * Retrieves the user profile information of the currently authenticated user.
	 *
	 * @return ResponseEntity with the user's profile data if the request is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if an exception occurs or the user is not authenticated.
	 */
	public ResponseEntity<User> getProfile();
	
	
	/**
	 * Updates the profile information of the currently authenticated user based on the provided map of user data.
	 *
	 * @param map A map containing updated user profile information.
	 * @return ResponseEntity with a success message if the profile is successfully updated (HTTP status OK),
	 *         or an error response with an appropriate status code if the update fails or an exception occurs.
	 */
	public ResponseEntity<String> updateProfile(Map<String, String>map);
	
}
