package com.pojo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "username")
	private String username;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String address;
	private String status;
	private String role;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;
	
	private String gender;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate; 
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin; 
	
}
