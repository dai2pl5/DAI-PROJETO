package com.boot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.boot.model.Coverage;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="package")
public class Package {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPackage;
	
	@NotBlank
	@Size(min=1, max=200)
	private String description;

	@Digits(integer=4, fraction = 2)
	private double basePrice;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore()
	private User user;
	
	@ManyToMany
	@JoinTable(name = "pack_has_coverage",
    joinColumns = @JoinColumn(name = "package_id"),
    inverseJoinColumns = @JoinColumn(name = "coverage_id"))
	private Set<Coverage> coverages;
	
	public Package() {}
	
	public Package(String description, double basePrice, User user) {
		
		this.description = description;
		this.basePrice = basePrice;
		this.user = user;
		this.coverages = new HashSet<Coverage>();
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
	
	public Set<Coverage> getCoverages() {
		return coverages;
	}

	public void setCoverages(Set<Coverage> coverages) {
		this.coverages = coverages;
	}

	
}
