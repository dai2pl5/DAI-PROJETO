package com.boot.projection;

import java.util.List;

import com.boot.model.Insurance;
public class InsurancesAndInsurerPayload {
	
	private List<Insurance> insurances;
	private List<String> insurerNames;
	public InsurancesAndInsurerPayload(List<Insurance> insurances, List<String> insurerNames) {
		this.insurances = insurances;
		this.insurerNames = insurerNames;
	}
	
	public List<Insurance> getInsurances() {
		return insurances;
	}
	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}
	public List<String> getInsurerNames() {
		return insurerNames;
	}
	public void setInsurerNames(List<String> insurerNames) {
		this.insurerNames = insurerNames;
	}
	
	
	
	
}
