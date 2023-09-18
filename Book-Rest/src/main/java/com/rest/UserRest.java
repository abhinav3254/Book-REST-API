package com.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.User;

/**
 * The UserRest interface defines the REST endpoints for user-related operations in the system.
 * These endpoints include user registration, login, checking admin privileges, retrieving user profiles,
 * and updating user profiles.
 */
@RequestMapping("/user")
public interface UserRest {
	
	/**
     * Registers a new user in the system based on the provided user details.
     *
     * @param requestMap A map containing user registration information.
     * @return ResponseEntity with a success message if user registration is successful (HTTP status OK),
     *         or an error response with an appropriate status code if registration fails or an exception occurs.
     */
	@PostMapping("/signup")
	ResponseEntity<String> signUp(@RequestBody(required = true)Map<String, String> requestMap);
	
	/**
     * Authenticates a user based on the provided login credentials.
     *
     * @param requestMap A map containing user login credentials such as username and password.
     * @return ResponseEntity with a success message if login is successful (HTTP status OK),
     *         or an error response with an appropriate status code if authentication fails or an exception occurs.
     */
	@PostMapping("/login")
	ResponseEntity<String> logIn(@RequestBody(required = true)Map<String, String> requestMap);
	
	/**
     * Checks if the currently authenticated user has administrative privileges.
     *
     * @return ResponseEntity with a success message if the user has admin privileges (HTTP status OK),
     *         or an error response with an appropriate status code if the user is not an admin or an exception occurs.
     */
	@GetMapping("/isAdmin")
	ResponseEntity<String> isAdminCheck();
	
	/**
     * Retrieves the user profile information of the currently authenticated user.
     *
     * @return ResponseEntity with the user's profile data if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs or the user is not authenticated.
     */
	@GetMapping("/profile")
	public ResponseEntity<User> getUser();
	
	
	/**
     * Updates the profile information of the currently authenticated user based on the provided map of user data.
     *
     * @param map A map containing updated user profile information.
     * @return ResponseEntity with a success message if the profile is successfully updated (HTTP status OK),
     *         or an error response with an appropriate status code if the update fails or an exception occurs.
     */
	@PostMapping("/updateProfile")
	public ResponseEntity<String> updateProfile(@RequestBody(required = true)Map<String, String>map);
}
