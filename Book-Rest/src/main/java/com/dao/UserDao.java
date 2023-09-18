package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pojo.User;


/**
 * The UserDao interface provides data access methods for managing user data in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	
	/**
     * Retrieves a user entity from the database based on the provided username.
     *
     * @param username The username of the user to retrieve.
     * @return The user entity if found, or null if no matching user is found in the database.
     */
	@Query(nativeQuery = true,value = "select * from user where username=:username")
	User getUserByUserName(@Param("username")String username);
	
}
