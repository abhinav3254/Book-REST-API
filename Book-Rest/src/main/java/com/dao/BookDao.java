package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pojo.Book;


/**
 * The BookDao interface provides data access methods for managing books in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
public interface BookDao extends JpaRepository<Book, Integer> {
	
	
	/**
     * Searches for books by various criteria such as title, author, publisher, price, description, or category.
     *
     * @param value The search value to match against book attributes.
     * @return A list of books that match the search criteria.
     */
	@Query(nativeQuery = true,value = "SELECT book.*\r\n"
			+ "FROM book\r\n"
			+ "LEFT JOIN publishers ON book.publishers_id = publishers.id\r\n"
			+ "LEFT JOIN author ON book.author_id = author.id\r\n"
			+ "WHERE\r\n"
			+ "    publishers.publisher_name LIKE %:value%\r\n"
			+ "    OR author.author_name LIKE %:value%\r\n"
			+ "    OR book.title LIKE %:value%\r\n"
			+ "    OR book.price LIKE %:value%\r\n"
			+ "    OR book.description LIKE %:value%\r\n"
			+ "    OR book.category LIKE %:value%;\r\n"
			+ "")
	public List<Book> findBookByValue(String value);
	
	/**
     * Searches for books by category.
     *
     * @param value The category to search for.
     * @return A list of books that belong to the specified category.
     */
	@Query(nativeQuery = true,value = "select * from book where category like %:value%")
	public List<Book> findBookByCategory(String value);
	
	
	/**
     * Retrieves a list of upcoming books that are scheduled for release in the future.
     *
     * @return A list of upcoming books.
     * 
     * for future publish book date
     */
	@Query(nativeQuery = true,value = "SELECT * FROM book WHERE publish_date > NOW()")
	public List<Book> upcomingBooks();
	
	
}
