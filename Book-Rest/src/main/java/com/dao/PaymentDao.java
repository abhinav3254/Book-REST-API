package com.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pojo.Payment;

/**
 * The PaymentDao interface provides data access methods for managing payment information in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
	
	/**
     * Retrieves all payment records associated with a specific user.
     *
     * @param uid The user ID.
     * @return Payment object containing payment details for the user.
     */
	@Query(nativeQuery = true,value = "select * from payment where user_id =:uid")
	public Payment getAllPaymentByUser(Integer uid);
	
	
	/**
     * Deletes all payment records associated with a specific user.
     *
     * @param uid The user ID.
     */
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "delete from payment where user_id =:uid")
	void deletePaymentByUserId(Integer uid);

}
