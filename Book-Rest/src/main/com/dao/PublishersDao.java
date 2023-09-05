package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pojo.Publishers;

public interface PublishersDao extends JpaRepository<Publishers, Integer> {

}
