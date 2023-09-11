package com.pojo;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	private String nationality;
	
	private String gender;

	public Integer getId() {
		return id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
