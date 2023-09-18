package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Book;


/**
 * The BookRest interface defines REST endpoints for managing books in the system.
 * Users can use these endpoints to add, retrieve, search, and update book information.
 */
@RequestMapping("/book")
public interface BookRest {
	
	
	/**
     * Adds a new book to the system based on the provided book details.
     *
     * @param map A map containing book information.
     * @return ResponseEntity with a success message if the book is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@PostMapping("/add")
	public ResponseEntity<String> addBook(@RequestBody(required = true)Map<String, String>map);
	
	/**
     * Retrieves a list of all books in the system.
     *
     * @return ResponseEntity with a list of books if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/all")
	public ResponseEntity<List<Book>> getAllBooks();
	
	/**
     * Searches for books based on the provided search criteria.
     *
     * @param search The search criteria.
     * @return ResponseEntity with a list of matching books if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if the search fails or an exception occurs.
     */
	@GetMapping("/search/{search}")
	public ResponseEntity<List<Book>> searchBooks(@PathVariable String search);
	
	
	/**
     * Retrieves a book by its unique identifier.
     *
     * @param id The unique identifier of the book.
     * @return ResponseEntity with the book if found (HTTP status OK),
     *         or an error response with an appropriate status code if the book is not found or an exception occurs.
     */
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable String id);
	
	
	/**
     * Retrieves a list of book suggestions based on user preferences or search history.
     *
     * @return ResponseEntity with a list of book suggestions if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/suggest")
	public ResponseEntity<List<String>> getBookSuggestion();
	
	/**
     * Retrieves a list of books by category.
     *
     * @param category The category of books to retrieve.
     * @return ResponseEntity with a list of books in the specified category if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Book>> getBookByCategory(@PathVariable String category);
	
	
	/**
     * Updates the quantity of a book based on the provided information.
     *
     * @param map A map containing the book ID and the new quantity.
     * @return ResponseEntity with a success message if the update is successful (HTTP status OK),
     *         or an error response with an appropriate status code if the update fails or an exception occurs.
     */
	@PostMapping("/updateQuantity")
	public ResponseEntity<String> updateQuantity(@RequestBody(required = true)Map<String, String>map);
	
	
	/**
     * Retrieves a list of upcoming books that are scheduled for release.
     *
     * @return ResponseEntity with a list of upcoming books if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/upcomingBooks")
	public ResponseEntity<List<Book>> getUpcomingBooks();
	
}
