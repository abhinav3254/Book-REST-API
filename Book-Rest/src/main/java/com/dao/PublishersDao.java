package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pojo.Publishers;

/**
 * The PublishersDao interface provides data access methods for managing publisher information in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
public interface PublishersDao extends JpaRepository<Publishers, Integer> {

}
