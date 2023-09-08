package com.restimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.Categories;
import com.rest.CategoriesRest;
import com.service.CategoriesService;


@RestController
public class CategoriesRestImpl implements CategoriesRest {
	
	@Autowired
	private CategoriesService categoriesService;

	@Override
	public ResponseEntity<String> addCategory(Map<String, String> map) {
		try {
			return categoriesService.addCategory(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Categories>> getAllCategories() {
		try {
			
			return categoriesService.getAllCategories();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Categories>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
