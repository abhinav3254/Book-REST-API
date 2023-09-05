package com.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.AuthorDao;
import com.dao.BookDao;
import com.dao.PublishersDao;
import com.jwt.JwtFilter;
import com.pojo.Author;
import com.pojo.Book;
import com.pojo.Publishers;
import com.service.BookService;


@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private PublishersDao publishersDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private JwtFilter jwtFilter;

	@Override
	public ResponseEntity<String> addBook(Map<String, String> map) {
		try {
			if (jwtFilter.isAdmin()) {
				Book book = configBook(map);
				
				if (!Objects.isNull(book)) {
					bookDao.save(book);
					return new ResponseEntity<String>(Constants.designMessage("ADDED BOOKS"),HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(Constants.designMessage("SOME VALUES ARE MISSING"),HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<String>(Constants.designMessage("UNAUTHORIZED ACCESS"),HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL SERVER ERROR"),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private Book configBook(Map<String, String>map) {
		try {
			Book book = new Book();
			Integer authorId = Integer.parseInt(map.get("authorId"));
			
			Integer publisherId = Integer.parseInt(map.get("publisherId"));
			
			Optional<Author> author = authorDao.findById(authorId);
			Optional<Publishers> publishers = publishersDao.findById(publisherId);
			
			book.setAuthor(author.get());
			book.setPublishers(publishers.get());
			book.setCategory(map.get("category"));
			book.setDescription(map.get("description"));
			book.setGenre(map.get("genre"));
			book.setImageUrl(map.get("image"));
			book.setPrice(map.get("price"));
			book.setTitle(map.get("title"));
			
			return book;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("SOMETHING WENT WRONG IN BOOK SERVICE IMPL CLASS");
	}

	@Override
	public ResponseEntity<List<Book>> getAllBook() {
		try {
			List<Book> listBooks = bookDao.getAllBooks();
			return new ResponseEntity<List<Book>>(listBooks,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> searchBooks(Map<String, String> map) {
		try {
			String value = map.get("value");
			List<Book> listBook = bookDao.findBookByValue(value);
			return new ResponseEntity<List<Book>>(listBook,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
