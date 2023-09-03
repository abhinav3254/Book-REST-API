package com.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.UserDao;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	com.pojo.User user;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user = userDao.getUserByUserName(username);
		if (user != null) {
			return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User Not Find By the email"+username);
		}
	}
	
	public com.pojo.User getUserDetails() {
		return user;
	}

}