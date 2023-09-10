package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dao.BookDao;
import com.dao.RatingsDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Book;
import com.pojo.Ratings;
import com.pojo.User;
import com.service.RatingsService;


@Service
public class RatingsServiceImpl implements RatingsService {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RatingsDao ratingsDao;
	
	@Autowired
	private BookDao bookDao;

	@Override
	public ResponseEntity<String> addRating(Map<String, String> map) {
		try {
//			Integer ratingValue = Integer.parseInt(map.get("value"));
			
			Integer bookId = Integer.parseInt(map.get("bookId"));
			
			/*
			 * Note that I have not limited the user to one rating to
			 * one product so we have to limit it so do it 
			 * simply use native query for this
			 * */
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userToken = authentication.getName();
	        String username = jwtUtils.extractUsername(userToken);
	        User user = userDao.getUserByUserName(username);
	        
	        Ratings ratings = new Ratings();
	        
	        ratings.setRating(Double.parseDouble(map.get("rating")));
	        ratings.setUser(user);
	        
	        Optional<Book> optionalBook = bookDao.findById(bookId);
	        if (optionalBook.isPresent()) {
	            Book book = optionalBook.get();
	            // Continue with the rest of the code
	            List<Ratings> existingRatings = book.getListRatings();
	            existingRatings.add(ratings);
	            book.setListRatings(existingRatings);
	            ratingsDao.save(ratings);
		        bookDao.save(book);
	        } else {
	            // Handle the case where the book with the given ID doesn't exist
	        	System.out.println("Abhinav RatingsService Impl else case throw error here check once");
	        } 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
