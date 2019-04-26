package com.boot.payload;

import java.util.List;
import com.boot.model.Package;

public class FinalSimulatorRequest {

	private List<Package> packages;
	
	private Double[] finalPrices;
	
	public FinalSimulatorRequest() {}
	public FinalSimulatorRequest(List<Package> packages, Double[] finalPrices) {
		
		this.packages = packages;
		this.finalPrices = finalPrices;
	}
	public List<Package> getPackages() {
		return packages;
	}
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	public Double[] getFinalPrices() {
		return finalPrices;
	}
	public void setFinalPrices(Double[] finalPrices) {
		this.finalPrices = finalPrices;
	}
	
	
}
