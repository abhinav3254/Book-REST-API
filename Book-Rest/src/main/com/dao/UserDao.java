package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pojo.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	@Query(nativeQuery = true,value = "select * from user where username=:username")
	User getUserByUserName(@Param("username")String username);
	
}
