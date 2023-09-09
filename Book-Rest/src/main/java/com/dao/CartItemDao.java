package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pojo.CartItem;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> {
	
//	Find if book exists
	@Query(nativeQuery = true,value = "select * from cart_item where book_id=:bookId and user_id =:uid")
	public CartItem findBookInCartItem(Integer bookId,Integer uid);
	
	@Query(nativeQuery = true,value = "select * from cart_item where user_id =:userId")
	public List<CartItem> getAllItemsFromCart(Integer userId);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "delete from cart_item where user_id =:userId")
	public List<CartItem> deleteAllItemsFromCartByUserId(Integer userId);

}
