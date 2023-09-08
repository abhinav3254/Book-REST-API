package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Categories;


@Repository
public interface CategoriesDao extends JpaRepository<Categories, Integer> {

}
