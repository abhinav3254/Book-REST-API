package com.pojo;




import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String authorName;
	
	private LocalDate dateOfBirth;

	// getter and setters
	public Integer getId() {
		return id;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate localDate) {
		this.dateOfBirth = localDate;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
}
