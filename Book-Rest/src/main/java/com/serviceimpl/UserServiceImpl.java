package com.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.UserDao;
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
			//user.setRegisterDate(now);
			
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
				String messageBuild = "{"
						+ "\n message: user not found"
								+ "\n}"; 
				return new ResponseEntity<String>(messageBuild,HttpStatus.BAD_REQUEST); 
			} else {
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("username"), requestMap.get("password")));
				if (authentication.isAuthenticated()) {
					if (myUserDetailsService.getUserDetails().getStatus().equalsIgnoreCase("true")) {
						return new ResponseEntity<String>("{ \ntoken\" : \""+jwtUtils.generateToken(myUserDetailsService.getUserDetails().getUsername(), myUserDetailsService.getUserDetails().getRole())+"\n}",HttpStatus.OK);
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

}
