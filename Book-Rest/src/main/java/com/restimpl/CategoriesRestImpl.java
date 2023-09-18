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


/**
 * The CategoriesRestImpl class implements the CategoriesRest interface and serves as the REST controller
 * for handling category-related operations in the system. It provides endpoints for adding categories and
 * retrieving a list of all categories.
 */
@RestController
public class CategoriesRestImpl implements CategoriesRest {
	// Autowired CategoriesService for handling category-related operations
	@Autowired
	private CategoriesService categoriesService;

	
	/**
     * Adds a new category to the system based on the provided category details.
     *
     * @param map A map containing category information, including category name and description.
     * @return ResponseEntity with a success message if the category is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@Override
	public ResponseEntity<String> addCategory(Map<String, String> map) {
		try {
			return categoriesService.addCategory(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	/**
     * Retrieves a list of all categories in the system.
     *
     * @return ResponseEntity with a list of categories if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
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
