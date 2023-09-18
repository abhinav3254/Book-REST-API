package com.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.AuthorDao;
import com.jwt.JwtFilter;
import com.pojo.Author;
import com.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private AuthorDao authorDao;

	
	/**
	 * Adds a new author to the system.
	 *
	 * @param map A map containing author details such as name, date of birth, gender, and nationality.
	 * @return ResponseEntity with a success message if the author is successfully added (HTTP status OK),
	 *         or an error response with an appropriate status code if unauthorized or an exception occurs.
	 */
	@Override
	public ResponseEntity<String> addAuthor(Map<String, String> map) {
		try {
			 // Check if the user has admin privileges
			if (jwtFilter.isAdmin()) {
				// Configure and save the author using the provided map
				Author author = authorConfig(map);
				authorDao.save(author);
				return new ResponseEntity<String>(Constants.designMessage("AUTHOR SAVED"),HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(Constants.designMessage("UNAUTHORIZED ACCESS"),HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL SERVER ERROR"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**
	 * Configures an Author object based on the provided map containing author details.
	 *
	 * @param map A map containing author details.
	 * @return The configured Author object.
	 * @throws RuntimeException If there is an issue with the provided map data.
	 */
	private Author authorConfig(Map<String, String>map) {
		try {
			Author author = new Author();
			// Extract and set author details from the provided map
			author.setAuthorName(map.get("name"));
			String dateOfBirth = map.get("dob");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date date = dateFormat.parse(dateOfBirth);
			author.setDateOfBirth(date);
			author.setGender(map.get("gender"));
			author.setNationality("country");
			
			return author;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Throwing self defined exception so that it will be easy for me to understand
		throw new RuntimeException("Issue in Map in Author");
	}

	
	/**
	 * Retrieves a list of all authors in the system.
	 *
	 * @return ResponseEntity with a list of author objects (HTTP status OK),
	 *         or an error response with an internal server error status if an exception occurs.
	 */
	@Override
	public ResponseEntity<List<Author>> getAllAuthor() {
		try {
			List<Author> authorList = authorDao.findAll();
			return new ResponseEntity<List<Author>>(authorList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Author>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
