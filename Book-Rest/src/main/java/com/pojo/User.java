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

	// The @Id annotation indicates that 'id' is the primary key for this entity.
	// The @GeneratedValue annotation specifies the strategy for generating unique
	// IDs.
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
	private String gender;

	// The @Temporal annotation specifies that 'dateOfBirth', 'registerDate', and 'lastLogin' are temporal fields, meaning they store date and time information.
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBirth;

	// The @Temporal annotation specifies that 'dateOfBirth', 'registerDate', and 'lastLogin' are temporal fields, meaning they store date and time information.
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;

	// The @Temporal annotation specifies that 'dateOfBirth', 'registerDate', and 'lastLogin' are temporal fields, meaning they store date and time information.
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	// getter and setters

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getStatus() {
		return status;
	}

	public String getRole() {
		return role;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}
