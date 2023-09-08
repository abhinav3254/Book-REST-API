package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pojo.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
	
	@Query(nativeQuery = true,value = "select book.*,PUBLISHERS.id,author.id from book\r\n"
			+ "join PUBLISHERS on PUBLISHERS.id = book.publishers_id\r\n"
			+ "join author on author.id = book.author_id")
	public List<Book> getAllBooks();
	
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
	
	
	@Query(nativeQuery = true,value = "select * from book where category like %:value%")
	public List<Book> findBookByCategory(String value);
	
}
