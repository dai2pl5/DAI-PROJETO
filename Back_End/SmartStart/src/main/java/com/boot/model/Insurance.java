package com.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import com.boot.model.Home;

@Table
@Entity
public class Insurance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idInsurance;
	
	@Digits(integer=5, fraction=2)
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "home_id", nullable = false)
	private Home home;

	
	public Insurance() {}
	
	public Insurance(double price, Home home) {
		this.home = home;
		this.price = price;
	}

	public long getIdInsurance() {
		return idInsurance;
	}

	public void setIdInsurance(long idInsurance) {
		this.idInsurance = idInsurance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}
	
	
}
