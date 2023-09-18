package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Ratings;

/**
 * The RatingsDao interface provides data access methods for managing user ratings data in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
@Repository
public interface RatingsDao extends JpaRepository<Ratings, Integer> {

//  @Query(value = "SELECT COUNT(*) FROM ratings WHERE user_id = :userId AND book_id = :bookId", nativeQuery = true)
//  Integer countByUserAndBook(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
//
//  default boolean existsByUserAndBook(@Param("userId") Integer userId, @Param("bookId") Integer bookId) {
//      return countByUserAndBook(userId, bookId) > 0;
//  }
	
}
