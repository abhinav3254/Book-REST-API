package com.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
					return new ResponseEntity<String>(Constants.designMessage("ADDED BOOKS"), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(Constants.designMessage("SOME VALUES ARE MISSING"),
							HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<String>(Constants.designMessage("UNAUTHORIZED ACCESS"),
						HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL SERVER ERROR"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Book configBook(Map<String, String> map) {
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
			book.setImageUrl(map.get("image"));
			book.setPrice(map.get("price"));
			book.setTitle(map.get("title"));

			book.setIsbn(map.get("isbn"));
			book.setPageCount(map.get("pageCount"));
			if (map.get("status").contains("t")) {
				book.setBookStatus(true);
			} else {
				book.setBookStatus(false);
			}

			String dateString = map.get("date");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

			Date date = dateFormat.parse(dateString);
			System.out.println(date);
			
			book.setBookQuantity(0);

			book.setPublishDate(date);

			return book;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("SOMETHING WENT WRONG IN BOOK SERVICE IMPL CLASS");
	}

	@Override
	public ResponseEntity<List<Book>> getAllBook() {
		try {
			List<Book> listBooks = bookDao.findAll();
			
			return new ResponseEntity<List<Book>>(listBooks, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> searchBooks(String search) {
		try {
			String value = search;
			System.out.println((bookDao.findBookByValue(value)).toString());
			List<Book> listBook = bookDao.findBookByValue(value);
			return new ResponseEntity<List<Book>>(listBook, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Book> getBookById(String id) {
		try {
			
			Optional<Book> book = bookDao.findById(Integer.parseInt(id));
			
			if (Objects.isNull(book.get())) {
				return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<String>> getSuggestion() {
		try {
			List<Book> listBooks = bookDao.findAll();
			//
			List<String> listSuggest = new ArrayList<String>();

			for (int i = 0; i < listBooks.size(); i++) {
				String title = listBooks.get(i).getTitle();
				listSuggest.add(title);
				String authorName = listBooks.get(i).getAuthor().getAuthorName();
				listSuggest.add(authorName);
				String publisherName = listBooks.get(i).getPublishers().getPublisherName();
				listSuggest.add(publisherName);
			}

			return new ResponseEntity<List<String>>(listSuggest, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> getBookByCategory(String category) {
		try {
			List<Book> listBook = bookDao.findBookByCategory(category);
			return new ResponseEntity<List<Book>>(listBook, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateQuantity(Map<String, String> map) {
		try {
			
			System.out.println("called this one -----------");
			Integer bookId = Integer.parseInt(map.get("bookId"));
			Optional<Book> bookOptional = bookDao.findById(bookId);
			Book book = bookOptional.get();
			
			Integer quantity = Integer.parseInt(map.get("quantity"));
			
			book.setBookQuantity(quantity);
			
			System.out.println(" book quantity ----->  "+book.getBookQuantity());
			
			bookDao.save(book);
			
			return new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Book>> getUpcomingBooks() {
		try {
			List<Book> book = bookDao.upcomingBooks();
			
			return new ResponseEntity<List<Book>>(book,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
