package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pojo.CartItem;

/**
 * The CartItemDao interface provides data access methods for managing cart item information in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> {
	
	/**
     * Retrieves a cart item by book ID and user ID.
     *
     * @param bookId The ID of the book.
     * @param userId The ID of the user.
     * @return The cart item if found, or null if not found.
     */
//	Find if book exists
	@Query(nativeQuery = true,value = "select * from cart_item where book_id=:bookId and user_id =:uid")
	public CartItem findBookInCartItem(Integer bookId,Integer uid);
	
	/**
     * Retrieves a list of all cart items for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of cart items for the user.
     */
	@Query(nativeQuery = true,value = "select * from cart_item where user_id =:userId")
	public List<CartItem> getAllItemsFromCart(Integer userId);
	
	/**
     * Deletes all cart items associated with a specific user.
     *
     * @param userId The ID of the user.
     */
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "delete from cart_item where user_id =:userId")
	public void deleteAllItemsFromCartByUserId(Integer userId);

}
