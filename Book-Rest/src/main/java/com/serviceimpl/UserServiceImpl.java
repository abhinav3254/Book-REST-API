package com.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			
			System.out.println(requestMap.toString());
			User user = userDao.getUserByUserName(requestMap.get("username"));
			
			if (Objects.isNull(user)) {
				userDao.save(configUser(requestMap));
				return new ResponseEntity<String>("USER ADDED",HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("USERNAME ALREADY TAKEN",HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("SOMETHING WENT WRONG",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private User configUser(Map<String, String>map) {
		
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now();  
			
			User user = new User();
			
			user.setName(map.get("name"));
			user.setUsername(map.get("username"));
			user.setAddress(map.get("address"));
			user.setEmail(map.get("email"));
			user.setPassword(map.get("password"));
			user.setPhone(map.get("phone"));
			
			
	        
	        String gender = map.get("gender");
	        
	        String dateString = map.get("date");
	        
	        if(!dateString.isEmpty()) {
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

	@Override
	public ResponseEntity<String> logIn(Map<String, String> requestMap) {
		try {
			User user = userDao.getUserByUserName(requestMap.get("username"));
			if (Objects.isNull(user)) {
				return new ResponseEntity<String>(Constants.designMessage("user not found"),HttpStatus.BAD_REQUEST); 
			} else {
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("username"), requestMap.get("password")));
				if (authentication.isAuthenticated()) {
					if (myUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
//						set up last login
						user.setLastLogin(new Date());
						userDao.save(user);
						return new ResponseEntity<String>(jwtUtils.generateToken(myUserDetailsService.getUserDetails().getUsername(), myUserDetailsService.getUserDetails().getRole()),HttpStatus.OK);
					} else {
						String messageBuild = "{"
								+ "\n message: wait for admin approval"
										+ "\n}"; 
						return new ResponseEntity<>(messageBuild,HttpStatus.OK);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = "{"
				+ "\n message : SOMETHING WENT WRONG\n"
						+ "}";
		return new ResponseEntity<String>(Constants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> isAdminCheck() {
		try {
			if(jwtFilter.isAdmin()) {
				return new ResponseEntity<String>("1#1$",HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("2#2$",HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<User> getProfile() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userToken = authentication.getName();
	        String username = jwtUtils.extractUsername(userToken);
	        User user = userDao.getUserByUserName(username);
	        
	        
	        return new ResponseEntity<User>(user,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateProfile(Map<String, String> map) {
		try {
			System.out.println(map.toString());
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userToken = authentication.getName();
	        String username = jwtUtils.extractUsername(userToken);
	        User user = userDao.getUserByUserName(username);
	        
	        String name =  map.get("name");
	        String address = map.get("address");
	        
	        String email = map.get("email");
	        String phone = map.get("phone");
	        
	        String gender = map.get("gender");
	        
	        String dateString = map.get("date");
	        
			
			if(!name.isEmpty())
				user.setName(name);
			if (!address.isEmpty())
				user.setAddress(address);
			if(!dateString.isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				Date date = dateFormat.parse(dateString);
				user.setDateOfBirth(date);
			}
			if(!email.isEmpty())
				user.setEmail(email);
			if (!phone.isEmpty())
				user.setPhone(phone);
			if(!gender.isEmpty())
				user.setGender(gender);
			
			userDao.save(user);
			
			return new ResponseEntity<String>(HttpStatus.OK);
	        
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
