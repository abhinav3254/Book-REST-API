package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.constants.Constants;
import com.pojo.Book;
import com.rest.BookRest;
import com.service.BookService;

@RestController
public class BookRestImpl implements BookRest {
	
	@Autowired
	private BookService bookService;

	@Override
	public ResponseEntity<String> addBook(Map<String, String> map) {
		try {
			return bookService.addBook(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL SERVER ERROR!!"),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			return bookService.getAllBook();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> searchBooks(String search) {
		try {
			return bookService.searchBooks(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Book> getBookById(String id) {
		try {
			return bookService.getBookById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<String>> getBookSuggestion() {
		try {
			return bookService.getSuggestion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> getBookByCategory(String category) {
		try {
			return bookService.getBookByCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateQuantity(Map<String, String> map) {
		try {
			return bookService.updateQuantity(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
