package com.pojo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Publishers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String publisherName;
	private String country;
	
	// getter and setters
	public Integer getId() {
		return id;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public String getCountry() {
		return country;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	

}
