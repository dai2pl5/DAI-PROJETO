package com.boot.payload;

public class BuyInsuranceRequest {
	
	private long idHome;
	private double price;
	private long idInsurer;
	private long idPackage;
	public BuyInsuranceRequest(long idHome, double price, long idInsurer, long idPackage) {
		this.idHome = idHome;
		this.price = price;
		this.idInsurer = idInsurer;
		this.idPackage = idPackage;
	}
	public long getIdHome() {
		return idHome;
	}
	public void setIdHome(long idHome) {
		this.idHome = idHome;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getIdInsurer() {
		return idInsurer;
	}
	public void setIdInsurer(long idInsurer) {
		this.idInsurer = idInsurer;
	}
	public long getIdPackage() {
		return idPackage;
	}
	public void setIdPackage(long idPackage) {
		this.idPackage = idPackage;
	}
	
	
	
	

}
