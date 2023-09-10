package com.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	// latest change on 8 sept 2023
	private String isbn;
	private String pageCount;
	private boolean bookStatus;
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;
	// upto here
	private String title;
	private String price;
	@Column(length = 4000)
	private String description;
	private String category;
	
	
	@OneToMany
	private List<Ratings> ratings;
	
	
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
	public void setPrice(String price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	public boolean isBookStatus() {
		return bookStatus;
	}
	public void setBookStatus(boolean bookStatus) {
		this.bookStatus = bookStatus;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}
