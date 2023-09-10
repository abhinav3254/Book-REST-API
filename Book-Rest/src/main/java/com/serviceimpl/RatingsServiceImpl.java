package com.serviceimpl;

import java.util.Date;
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
import com.service.RatingService;


@Service
public class RatingsServiceImpl implements RatingService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private RatingsDao ratingsDao;
	
	@Autowired
	private BookDao bookDao;

	@Override
	public ResponseEntity<String> addRating(Map<String, String> map) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String userToken = authentication.getName();
	        String username = jwtUtils.extractUsername(userToken);
	        User user = userDao.getUserByUserName(username);
	        
	        
	        
	        Double ratingValue = Double.parseDouble(map.get("rating"));
	        Ratings ratings = new Ratings();
	        ratings.setRatingDate(new Date());
	        ratings.setUser(user);
	        ratings.setRatingValue(ratingValue);
	        
	        Optional<Book> bookOptional = bookDao.findById(Integer.parseInt(map.get("bookId")));
	        
	        ratings.setBook(bookOptional.get());
	        
	        ratingsDao.save(ratings);
	        
	        return new ResponseEntity<String>(HttpStatus.OK);
	       
	        
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			System.out.println("------------You can't place rating for same book for two times------------------------");
			return new ResponseEntity<String>("You can't place rating for two orders",HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
