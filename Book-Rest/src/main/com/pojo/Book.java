package com.pojo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Publishers publishers;
	
	@OneToOne
	private Author author;
	
	@Column(length = 2000)
	private String imageUrl;
	
	private String title;
	private String genre;
	private String price;
	@Column(length = 2000)
	private String description;
	private String category;
	
	// getters and setters
	
	public Integer getId() {
		return id;
	}
	public Publishers getPublishers() {
		return publishers;
	}
	public Author getAuthor() {
		return author;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getTitle() {
		return title;
	}
	public String getGenre() {
		return genre;
	}
	public String getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setPublishers(Publishers publishers) {
		this.publishers = publishers;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
