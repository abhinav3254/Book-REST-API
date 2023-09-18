package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pojo.Cart;

/**
 * The CartDao interface provides data access methods for managing user shopping carts in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
public interface CartDao extends JpaRepository<Cart, Integer> {
	/*
	@Query(nativeQuery = true,value = "SELECT cart.*, book.*,user.*\r\n"
			+ "FROM cart_books\r\n"
			+ "JOIN cart ON cart.id = cart_books.cart_id\r\n"
			+ "JOIN book ON book.id = cart_books.books_id\r\n"
			+ "JOIN user ON user.id = cart.user_id "
			+ "where user.id =:uid")
	public List<Cart> getAllItemsInCart(@Param("uid") String uid);
	

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM cart_books WHERE cart_id = :cartId", nativeQuery = true)
	void deleteCartBooksByCartId(@Param("cartId") String cartId);

		
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM cart WHERE user_id = :userId", nativeQuery = true)
	void deleteCartByUserId(@Param("userId") String userId);
	*/
	
	
	/**
     * Retrieves a list of user shopping carts by user ID.
     *
     * @param userId The ID of the user.
     * @return A list of user shopping carts.
     */
	@Query(nativeQuery = true,value = "select * from cart where user_id=:uid")
	public List<Cart> getCartByUserId(Integer uid);
	
}
