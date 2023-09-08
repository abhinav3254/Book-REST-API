package com.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;


@Data
@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@OneToOne
	private User user;
	@OneToOne
	private Book book;
	private double bookPrice;
	private int quantity;
	
	private Double cgst;
	
	private Double sgst;
	
	private Double discount;
	
	private Double finalPrice;
	

}
