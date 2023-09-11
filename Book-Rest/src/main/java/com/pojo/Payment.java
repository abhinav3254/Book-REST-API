package com.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


@Data
@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	
	@OneToOne
	private User user;
	
	private String cardNumber;
	
	private String bankName;
	
	private String nameOnCard;
	
	private String cvv;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate; 
	
	private Double amount;
	
	// getter and setters

	public Integer getPid() {
		return pid;
	}

	public User getUser() {
		return user;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public String getCvv() {
		return cvv;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
