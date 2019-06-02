package com.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import com.boot.model.Home;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore()
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private boolean active;
	private boolean rejected;
	@OneToOne
	private Package packageInsurer;
	
	public Insurance() {}
	
	public Insurance(double price, Home home, User user, boolean active, boolean rejected, Package packageInsurer) {
		this.home = home;
		this.price = price;
		this.user = user;
		this.active = active;
		this.rejected = rejected;
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

	public Package getPackageInsurer() {
		return packageInsurer;
	}

	public void setPackageInsurer(Package packageInsurer) {
		this.packageInsurer = packageInsurer;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
	
	
	
}
