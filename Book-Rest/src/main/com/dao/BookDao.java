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
	
	@Query(nativeQuery = true,value = "SELECT * from book,author,publishers where category like %:value% OR\r\n"
			+ "						 description like %:value% OR\r\n"
			+ "                         genre like %:value% OR\r\n"
			+ "                         price like %:value% OR\r\n"
			+ "                         title like %:value% OR\r\n"
			+ "						 author.author_name like %:value% OR\r\n"
			+ "                         publishers.country like %:value% OR\r\n"
			+ "                         publishers.publisher_name like %:value%;")
	public List<Book> findBookByValue(String value);
	
}
