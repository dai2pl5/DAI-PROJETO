package com.boot.payload;

import com.boot.model.User;



public class ConfigurationRequest {
	

	private long idConfig;
	

	private String designation;
	
	private double tax;
	

	private User user;
	
	public ConfigurationRequest(){}
	
	public ConfigurationRequest(String designation, double tax, User user) {
		this.designation = designation;
		this.tax = tax;
		this.user = user;
	}

	public long getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(long idConfig) {
		this.idConfig = idConfig;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
