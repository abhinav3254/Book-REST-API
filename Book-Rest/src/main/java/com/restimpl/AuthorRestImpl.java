package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.pojo.Author;
import com.rest.AuthorRest;
import com.service.AuthorService;

@RestController
public class AuthorRestImpl implements AuthorRest {
	
	@Autowired
	private AuthorService authorService;

	@Override
	public ResponseEntity<String> addAuthor(Map<String, String> map) {
		try {
			return authorService.addAuthor(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL SERVER ERROR!"),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Author>> getAllAuthor() {
		try {
			return authorService.getAllAuthor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Author>> (HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
