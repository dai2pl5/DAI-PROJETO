/*package com.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table
public class Package {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPackage;
	
	@NotBlank
	@Size(min=1, max=200)
	private String description;
	
	@NotBlank
	@Size(min=0, max=2000)
	private double basePrice;
	
	@ManyToOne
	private User user;
	
	public Package() {}
	
	public Package(String description, double basePrice, User user) {
		
		this.description = description;
		this.basePrice = basePrice;
		this.user = user;
	}
	
	
}*/
