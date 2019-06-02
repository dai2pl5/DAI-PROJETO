package com.boot.projection;


import java.util.List;

import com.boot.model.Insurance;

public class InsuranceAndClients {

	private List<UserProjection> clients;
	private List<Insurance> insurances;
	public InsuranceAndClients(List<UserProjection> clients, List<Insurance> insurances) {
		this.clients = clients;
		this.insurances = insurances;
	}
	public List<UserProjection> getClients() {
		return clients;
	}
	public void setClients(List<UserProjection> clients) {
		this.clients = clients;
	}
	public List<Insurance> getInsurances() {
		return insurances;
	}
	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}
	
	
	
	
}
