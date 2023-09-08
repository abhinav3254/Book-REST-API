package com.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Categories;

@RequestMapping("/categories")
public interface CategoriesRest {
	
	@PostMapping("/add")
	public ResponseEntity<String> addCategory(@RequestBody(required = true)Map<String, String>map);
	
//	@PatchMapping("/update")
//	public ResponseEntity<String> updateCategory(@RequestBody(required = true)Map<String, String> map);
//	
//	@DeleteMapping("/delete")
//	public ResponseEntity<String> deleteCategory(@RequestBody(required = true)Map<String, String>map);
	
	@GetMapping("/all")
	public ResponseEntity<List<Categories>> getAllCategories();

}
