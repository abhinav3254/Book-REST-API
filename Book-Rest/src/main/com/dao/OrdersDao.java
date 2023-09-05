package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pojo.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer> {
	
	@Query(nativeQuery = true,value = "select orders_books.*,orders.*,book.*,user.* from orders_books\n"
			+ "join orders on orders_books.orders_id = orders.id\n"
			+ "join book on book.id = orders_books.books_id\n"
			+ "join user on user.id = orders.user_id \n"
			+ "where user.id =:uid")
	public List<Orders> getAllOrders(@Param("uid")String uid);

}
