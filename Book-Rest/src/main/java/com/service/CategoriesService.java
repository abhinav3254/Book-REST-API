package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Categories;

public interface CategoriesService {
	
	public ResponseEntity<String> addCategory(Map<String, String> map);
	
	public ResponseEntity<List<Categories>> getAllCategories();

}
