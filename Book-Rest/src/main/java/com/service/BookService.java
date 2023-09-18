package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Book;


/**
 * The BookService interface defines methods for managing book-related operations in the system.
 * It provides functionality to add books, retrieve book lists, search for books, get book details by ID,
 * get book suggestions, get books by category, update book quantity, and retrieve upcoming book releases.
 */
public interface BookService {
	
	
	/**
	 * Adds a new book to the system based on the provided book details.
	 *
	 * @param map A map containing book information.
	 * @return ResponseEntity with a success message if the book is added successfully (HTTP status OK),
	 *         or an error response with an appropriate status code if the addition fails.
	 */
	public ResponseEntity<String> addBook(Map<String, String>map);
	
	/**
	 * Retrieves a list of all books in the system.
	 *
	 * @return ResponseEntity with a list of books if the request is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if an exception occurs.
	 */
	public ResponseEntity<List<Book>> getAllBook();
	
	
	/**
	 * Searches for books based on the provided search criteria.
	 *
	 * @param search The search criteria.
	 * @return ResponseEntity with a list of matching books if the request is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if the search fails.
	 */
	public ResponseEntity<List<Book>> searchBooks(String search);
	
	
	/**
	 * Retrieves a book by its unique identifier.
	 *
	 * @param id The unique identifier of the book.
	 * @return ResponseEntity with the book if found (HTTP status OK),
	 *         or an error response with an appropriate status code if the book is not found.
	 */
	public ResponseEntity<Book> getBookById(String id);
	
	
	/**
	 * Retrieves a list of book suggestions based on user preferences or search history.
	 *
	 * @return ResponseEntity with a list of book suggestions if the request is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if an exception occurs.
	 */
	public ResponseEntity<List<String>> getSuggestion();
	
	
	/**
	 * Retrieves a list of books by category.
	 *
	 * @param category The category of books to retrieve.
	 * @return ResponseEntity with a list of books in the specified category if the request is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if an exception occurs.
	 */
	public ResponseEntity<List<Book>> getBookByCategory(String category);
	
	
	/**
	 * Updates the quantity of a book based on the provided information.
	 *
	 * @param map A map containing the book ID and the new quantity.
	 * @return ResponseEntity with a success message if the update is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if the update fails.
	 */
	public ResponseEntity<String> updateQuantity(Map<String, String>map);
	
	
	/**
	 * Retrieves a list of upcoming books that are scheduled for release.
	 *
	 * @return ResponseEntity with a list of upcoming books if the request is successful (HTTP status OK),
	 *         or an error response with an appropriate status code if an exception occurs.
	 */
	public ResponseEntity<List<Book>> getUpcomingBooks();
	
}
