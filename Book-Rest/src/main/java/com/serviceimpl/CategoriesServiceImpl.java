package com.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.constants.Constants;
import com.dao.CategoriesDao;
import com.jwt.JwtFilter;
import com.pojo.Categories;
import com.service.CategoriesService;


@Service
public class CategoriesServiceImpl implements CategoriesService {
	
	@Autowired
	private JwtFilter jwtFilter;
	@Autowired
	private CategoriesDao categoriesDao;

	
	/**
	 * Adds a new category to the system, accessible only to admin.
	 *
	 * @parameter map A map containing properties to configure the new category.
	 * @return ResponseEntity with a success message if the category is added (HTTP status OK),
	 *         or an "UNAUTHORIZED" status if the user is not an admin,
	 *         or an error message with an internal server error status if an exception occurs.
	 */
	@Override
	public ResponseEntity<String> addCategory(Map<String, String> map) {
		try {
			if (jwtFilter.isAdmin()) {
				String cName = map.get("name");
				
				Categories categories = new Categories();
				
				categories.setCategoryName(cName);
				
				categoriesDao.save(categories);
				
				return new ResponseEntity<String>(Constants.designMessage("Added Category name :- "+cName),HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("INTERNAL ISSUE",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Retrieves a list of all categories from the system.
	 *
	 * @return ResponseEntity containing a list of Categories if successful (HTTP status OK),
	 *         or an empty list with an internal server error status if an exception occurs.
	 */
	@Override
	public ResponseEntity<List<Categories>> getAllCategories() {
		try {
			List<Categories> listCategories = categoriesDao.findAll();
			
			return new ResponseEntity<List<Categories>>(listCategories,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Categories>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
