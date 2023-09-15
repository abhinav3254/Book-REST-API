package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pojo.Orders;

public interface OrdersDao extends JpaRepository<Orders, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM Orders WHERE user_id = :userId")
	public List<Orders> getAllOrders(@Param("userId") String userId);

	
	@Query(nativeQuery = true,value = "SELECT * FROM orders WHERE cart_id IS NULL and  user_id =:userId")
	public List<Orders> getRefund(Integer userId);
}
