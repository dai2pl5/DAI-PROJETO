package com.boot.payload;


import com.boot.model.Home;
import com.boot.model.Package;
import com.boot.model.User;

public class InsuranceRequest {
	

	private long idInsurance;
	
	private double price;
	
	private Home home;
	
	private User user;

	private boolean active;
	
	public Package getPackageInsurer() {
		return packageInsurer;
	}

	public void setPackageInsurer(Package packageInsurer) {
		this.packageInsurer = packageInsurer;
	}

	private Package packageInsurer;
	
	public InsuranceRequest() {}
	
	public InsuranceRequest(double price, Home home, User user, boolean active,Package packageInsurer) {
		this.home = home;
		this.price = price;
		this.user = user;
		this.active = active;
		this.packageInsurer = packageInsurer;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
