package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Categories;


/**
 * The CategoriesDao interface provides data access methods for managing category information in the database.
 * It extends JpaRepository to leverage Spring Data JPA's built-in repository functionality.
 */
@Repository
public interface CategoriesDao extends JpaRepository<Categories, Integer> {

}
