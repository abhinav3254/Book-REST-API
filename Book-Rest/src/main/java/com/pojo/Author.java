package com.pojo;



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

	// getter and setters
	public Integer getId() {
		return id;
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
