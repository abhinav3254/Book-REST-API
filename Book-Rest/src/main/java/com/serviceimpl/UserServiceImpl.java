package com.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.UserDao;
import com.jwt.JwtFilter;
import com.jwt.JwtUtils;
import com.jwt.MyUserDetailsService;
import com.pojo.User;
import com.service.UserService;


/**
 * This class provides implementations for managing user-related operations in the system.
 * It includes methods for user sign-up, login, profile management, and admin checks.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private JwtFilter jwtFilter;

	/**
     * Registers a new user in the system when they sign up. This method is called with a map of user details.
     *
     * @parameter requestMap A map containing user registration information.
     * @return ResponseEntity with a success message if the user is added (HTTP status OK),
     *         or a message indicating that the username is already taken (HTTP status OK),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {

			System.out.println(requestMap.toString());
			User user = userDao.getUserByUserName(requestMap.get("username"));

			if (Objects.isNull(user)) {
				userDao.save(configUser(requestMap));
				return new ResponseEntity<String>("USER ADDED", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("USERNAME ALREADY TAKEN", HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Configures and creates a User object based on a provided map of properties.
     *
     * @parameter map A map containing properties to configure the User object.
     * @return A User object with properties set as specified in the map.
     */
	private User configUser(Map<String, String> map) {

		try {
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//			LocalDateTime now = LocalDateTime.now();  

			User user = new User();

			user.setName(map.get("name"));
			user.setUsername(map.get("username"));
			user.setAddress(map.get("address"));
			user.setEmail(map.get("email"));
			user.setPassword(map.get("password"));
			user.setPhone(map.get("phone"));

			String gender = map.get("gender");

			String dateString = map.get("date");

			if (!dateString.isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				Date date = dateFormat.parse(dateString);
				user.setDateOfBirth(date);
			}

			user.setRegisterDate(new Date());

			user.setGender(gender);

			user.setStatus("true");
			user.setRole("user");

			return user;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
     * Logs a user into the system when they provide valid credentials. Returns a JWT token upon successful login.
     *
     * @parameter requestMap A map containing user login credentials.
     * @return ResponseEntity with a JWT token if authentication is successful (HTTP status OK),
     *         or a message indicating an incorrect username or password (HTTP status BAD_REQUEST),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<String> logIn(Map<String, String> requestMap) {
		try {
			User user = userDao.getUserByUserName(requestMap.get("username"));

			if (Objects.isNull(user)) {
				return new ResponseEntity<String>(Constants.designMessage("user not found"), HttpStatus.BAD_REQUEST);
			} else {
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("username"),
								requestMap.get("password")));
				if (authentication.isAuthenticated()) {
					if (myUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
//						set up last login
						user.setLastLogin(new Date());
						userDao.save(user);
						return new ResponseEntity<String>(
								jwtUtils.generateToken(myUserDetailsService.getUserDetails().getUsername(),
										myUserDetailsService.getUserDetails().getRole()),
								HttpStatus.OK);
					} else {
						String messageBuild = "{" + "\n message: wait for admin approval" + "\n}";
						return new ResponseEntity<>(messageBuild, HttpStatus.OK);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Checks if the user making the request is an administrator and returns a unique value for identification.
     *
     * @return ResponseEntity with a unique value for an admin user (HTTP status OK),
     *         or a unique value for a non-admin user (HTTP status OK),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<String> isAdminCheck() {
		try {
			if (jwtFilter.isAdmin()) {
				return new ResponseEntity<String>("1#1$", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("2#2$", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	 /**
     * Retrieves and returns the profile details of the currently authenticated user.
     *
     * @return ResponseEntity containing the user's profile details if successful (HTTP status OK),
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<User> getProfile() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userToken = authentication.getName();
			String username = jwtUtils.extractUsername(userToken);
			User user = userDao.getUserByUserName(username);

			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Allows the user to update their profile information.
     *
     * @parameter map A map containing updated user profile details.
     * @return ResponseEntity with an OK status if the profile is updated successfully,
     *         or an error message with an internal server error status if an exception occurs.
     */
	@Override
	public ResponseEntity<String> updateProfile(Map<String, String> map) {
		try {
			System.out.println(map.toString());

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userToken = authentication.getName();
			String username = jwtUtils.extractUsername(userToken);
			User user = userDao.getUserByUserName(username);

			String name = map.get("name");
			String address = map.get("address");

			String email = map.get("email");
			String phone = map.get("phone");

			String gender = map.get("gender");

			String dateString = map.get("date");

			if (!name.isEmpty())
				user.setName(name);
			if (!address.isEmpty())
				user.setAddress(address);
			if (!dateString.isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				Date date = dateFormat.parse(dateString);
				user.setDateOfBirth(date);
			}
			if (!email.isEmpty())
				user.setEmail(email);
			if (!phone.isEmpty())
				user.setPhone(phone);
			if (!gender.isEmpty())
				user.setGender(gender);

			userDao.save(user);

			return new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
