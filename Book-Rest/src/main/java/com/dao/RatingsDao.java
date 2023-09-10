package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Ratings;


@Repository
public interface RatingsDao extends JpaRepository<Ratings, Integer> {

}
