package com.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pojo.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
	
	@Query(nativeQuery = true,value = "select * from payment where user_id =:uid")
	public Payment getAllPaymentByUser(Integer uid);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "delete from payment where user_id =:uid")
	void deletePaymentByUserId(Integer uid);

}
