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

}
