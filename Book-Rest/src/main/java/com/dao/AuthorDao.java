package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pojo.Author;


/**
 * The AuthorDao interface provides data access methods for managing authors in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
public interface AuthorDao extends JpaRepository<Author, Integer> {

}
