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

/**
 * The CategoriesRest interface defines REST endpoints for managing categories in the system.
 * Users can use these endpoints to add categories and retrieve all categories.
 */
@RequestMapping("/categories")
public interface CategoriesRest {
	
	/**
     * Adds a new category to the system based on the provided category data.
     *
     * @param map A map containing category information to be added.
     * @return ResponseEntity with a success message if the category is added successfully (HTTP status OK),
     *         or an error response with an appropriate status code if the addition fails or an exception occurs.
     */
	@PostMapping("/add")
	public ResponseEntity<String> addCategory(@RequestBody(required = true)Map<String, String>map);
	
	// /**
    //  * Updates an existing category in the system based on the provided category data.
    //  *
    //  * @param map A map containing updated category information.
    //  * @return ResponseEntity with a success message if the category is updated successfully (HTTP status OK),
    //  *         or an error response with an appropriate status code if the update fails or an exception occurs.
    //  */
    // @PatchMapping("/update")
    // public ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String, String> map);
    
    // /**
    //  * Deletes a category from the system based on the provided category data.
    //  *
    //  * @param map A map containing information about the category to be deleted.
    //  * @return ResponseEntity with a success message if the category is deleted successfully (HTTP status OK),
    //  *         or an error response with an appropriate status code if the deletion fails or an exception occurs.
    //  */
    // @DeleteMapping("/delete")
    // public ResponseEntity<String> deleteCategory(@RequestBody(required = true) Map<String, String> map);
    
	
	
	/**
     * Retrieves a list of all categories in the system.
     *
     * @return ResponseEntity with a list of categories if the request is successful (HTTP status OK),
     *         or an error response with an appropriate status code if an exception occurs.
     */
	@GetMapping("/all")
	public ResponseEntity<List<Categories>> getAllCategories();

}
