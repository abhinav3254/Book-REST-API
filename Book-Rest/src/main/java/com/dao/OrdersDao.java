package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pojo.Orders;

/**
 * The OrdersDao interface provides data access methods for managing order information in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
public interface OrdersDao extends JpaRepository<Orders, Integer> {
	
	/**
     * Retrieves a list of all orders placed by a specific user.
     *
     * @param userId The user ID.
     * @return List of Orders objects representing the user's orders.
     */
	@Query(nativeQuery = true, value = "SELECT * FROM Orders WHERE user_id = :userId")
	public List<Orders> getAllOrders(@Param("userId") String userId);

	/**
     * Retrieves a list of refund orders placed by a specific user.
     *
     * @param userId The user ID.
     * @return List of Orders objects representing the user's refund orders.
     */
	@Query(nativeQuery = true,value = "SELECT * FROM orders WHERE cart_id IS NULL and  user_id =:userId")
	public List<Orders> getRefund(Integer userId);
}
