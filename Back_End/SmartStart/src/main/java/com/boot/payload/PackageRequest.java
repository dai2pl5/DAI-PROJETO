package com.boot.payload;





import com.boot.model.User;


public class PackageRequest {

	private long idPackage;
	
	private String description;
	
	private double basePrice;
	
	private String[] coverages;
	
	private User user;
	
	public PackageRequest() {}
	
	public PackageRequest(String description, double basePrice, User user, String[] coverages) {
		
		this.description = description;
		this.basePrice = basePrice;
		this.user = user;
		this.coverages = coverages;
	}

	public long getIdPackage() {
		return idPackage;
	}

	public void setIdPackage(long idPackage) {
		this.idPackage = idPackage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getCoverages() {
		return coverages;
	}

	public void setCoverages(String[] coverages) {
		this.coverages = coverages;
	}


	
	
}
	
	