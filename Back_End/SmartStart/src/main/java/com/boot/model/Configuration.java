package com.boot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.boot.model.User;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity
public class Configuration {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idConfig;
	
	@NotBlank
	@Size(max=40)
	private String designation;
	
	@Digits(integer=4, fraction = 2)
	private double tax;
	
	@ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore()
	private User user;
	
	public Configuration(){}
	
	public Configuration(String designation, double tax, User user) {
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
