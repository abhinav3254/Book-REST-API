package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pojo.CartItem;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> {
	
//	Find if book exists
	@Query(nativeQuery = true,value = "select * from cart_item where book_id=:bookId")
	public CartItem findBookInCartItem(Integer bookId);
	
	@Query(nativeQuery = true,value = "select * from cart_item where user_id =:userId")
	public List<CartItem> getAllItemsFromCart(Integer userId);

}
