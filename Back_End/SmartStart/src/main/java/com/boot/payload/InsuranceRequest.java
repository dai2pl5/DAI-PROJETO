package com.boot.payload;


import com.boot.model.Home;

public class InsuranceRequest {
	
	private long idInsurance;

	private double price;
	

	private Home home;

	
	public InsuranceRequest() {}
	
	public InsuranceRequest(double price, Home home) {
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
