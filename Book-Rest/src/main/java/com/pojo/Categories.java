package com.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String categoryName;
	
	// getter and setters

	public Integer getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
