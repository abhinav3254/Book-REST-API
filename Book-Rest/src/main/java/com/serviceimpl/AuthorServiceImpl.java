package com.serviceimpl;

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
	
	@Override
	public ResponseEntity<String> addAuthor(Map<String, String> map) {
		try {
			if (jwtFilter.isAdmin()) {
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
	
	private Author authorConfig(Map<String, String>map) {
		try {
			Author author = new Author();
			
			author.setAuthorName(map.get("name"));
			
			return author;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Issue in Map in Author");
	}

}
