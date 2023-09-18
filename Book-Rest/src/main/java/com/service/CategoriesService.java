package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.pojo.Categories;


/**
 * The CategoriesService interface defines methods for managing product categories, including adding categories
 * and retrieving a list of all available categories.
 */
public interface CategoriesService {
	
	
	/**
     * Adds a new product category based on the provided map of category details.
     *
     * @param map A map containing category information, such as category name and description.
     * @return ResponseEntity with a success message if the category is successfully added (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails.
     */
	public ResponseEntity<String> addCategory(Map<String, String> map);
	
	
	/**
     * Retrieves a list of all product categories in the system.
     *
     * @return ResponseEntity with a list of categories if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	public ResponseEntity<List<Categories>> getAllCategories();

}
