package com.serviceimpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.BookDao;
import com.dao.RatingsDao;
import com.dao.UserDao;
import com.jwt.JwtUtils;
import com.pojo.Book;
import com.pojo.Ratings;
import com.pojo.User;
import com.service.RatingsService;

//@Service
public class RatingsServiceImpl implements RatingsService {

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
//	private RatingsDao ratingsDao;
	
	@Override
	public ResponseEntity<String> postRating(Map<String, String> map) {
		try {
			Ratings ratings = ratingsConfig(map);
			
			if (Objects.isNull(ratings)) {
				return new ResponseEntity<String>(Constants.designMessage("SOME VALUES ARE INCORRECT"),HttpStatus.BAD_REQUEST);
			} else {
//				ratingsDao.save(ratings);
				return new ResponseEntity<String>(Constants.designMessage("RATING POSTED"),HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("SOMETHING WENT WRONG!!"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private Ratings ratingsConfig(Map<String, String>map) {
		try {
			Ratings ratings = new Ratings();
			
			Authentication authentication = SecurityContextHolder. getContext(). getAuthentication();
			String token = authentication.getName();
			String username = jwtUtils.extractUsername(token);
			User user = userDao.getUserByUserName(username);
			
			ratings.setUser(user);
			
			String bookId = map.get("bookId");
			Optional<Book> book = bookDao.findById(Integer.parseInt(bookId));
			
			ratings.setBook(book.get());
			
			ratings.setRatingDate(LocalDate.now());
			
			ratings.setRating(Double.parseDouble(map.get("rating")));
			ratings.setReview(map.get("review"));
			
			ratings.setBook(null);
			
			return ratings;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("ratingsConfig error");
	}

}
