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

/**
 * The BookRestImpl class implements the BookRest interface and serves as the REST controller
 * for handling book-related operations in the system. It provides endpoints for adding, retrieving,
 * searching, updating, and managing books in the system.
 */
@RestController
public class BookRestImpl implements BookRest {
	
	// Autowired BookService for handling book-related operations
	@Autowired
	private BookService bookService;

	/**
     * Adds a new book to the system based on the provided book details.
     *
     * @param map A map containing book information.
     * @return ResponseEntity with a success message if the book is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> addBook(Map<String, String> map) {
		try {
			return bookService.addBook(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(Constants.designMessage("INTERNAL SERVER ERROR!!"),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Retrieves a list of all books in the system.
     *
     * @return ResponseEntity with a list of books if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			return bookService.getAllBook();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Searches for books based on the provided search criteria.
     *
     * @param search The search criteria.
     * @return ResponseEntity with a list of matching books if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if the search fails or an exception occurs.
     */
	@Override
	public ResponseEntity<List<Book>> searchBooks(String search) {
		try {
			return bookService.searchBooks(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Retrieves a book by its unique identifier.
     *
     * @param id The unique identifier of the book.
     * @return ResponseEntity with the book if found (HTTP status OK),
     *         or an error response with an appropriate status code if the book is not found or an exception occurs.
     */
	@Override
	public ResponseEntity<Book> getBookById(String id) {
		try {
			return bookService.getBookById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Retrieves a list of book suggestions based on user preferences or search history.
     *
     * @return ResponseEntity with a list of book suggestions if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<String>> getBookSuggestion() {
		try {
			return bookService.getSuggestion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Retrieves a list of books by category.
     *
     * @param category The category of books to retrieve.
     * @return ResponseEntity with a list of books in the specified category if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Book>> getBookByCategory(String category) {
		try {
			return bookService.getBookByCategory(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Updates the quantity of a book based on the provided information.
     *
     * @param map A map containing the book ID and the new quantity.
     * @return ResponseEntity with a success message if the update is successful (HTTP status OK),
     *         or an error response with an appropriate status code if the update fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> updateQuantity(Map<String, String> map) {
		try {
			return bookService.updateQuantity(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
     * Retrieves a list of upcoming books that are scheduled for release.
     *
     * @return ResponseEntity with a list of upcoming books if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@Override
	public ResponseEntity<List<Book>> getUpcomingBooks() {
		try {
			return bookService.getUpcomingBooks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Book>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
