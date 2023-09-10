package com.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	
	private Double averageRating;
	
	@OneToMany
	private List<Ratings> listRatings;
	
	
}
