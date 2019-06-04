package com.boot.projection;

import java.util.Set;

import com.boot.model.Home;

public class HousesAndInsurancesProjection {

	private Set<Home> houses;
	private Boolean[] isInsured;
	
	
	public HousesAndInsurancesProjection(Set<Home> houses, Boolean[] isInsured) {
		this.houses = houses;
		this.isInsured = isInsured;
	}
	public Set<Home> getHouses() {
		return houses;
	}
	public void setHouses(Set<Home> houses) {
		this.houses = houses;
	}
	public Boolean[] getIsInsured() {
		return isInsured;
	}
	public void setIsInsured(Boolean[] isInsured) {
		this.isInsured = isInsured;
	}
	
	
}
