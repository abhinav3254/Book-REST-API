package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pojo.Author;

public interface AuthorDao extends JpaRepository<Author, Integer> {

}
