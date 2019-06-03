package com.boot.payload;

import java.util.List;
import com.boot.model.Package;

public class FinalSimulatorRequest {

	private List<Package> packages;
	
	private Double[] finalPrices;
	private List<String> insurerNames;
	private long[] idInsurer;
	public FinalSimulatorRequest() {}
	public FinalSimulatorRequest(List<Package> packages, Double[] finalPrices,List<String> insurerNames,long[] idInsurer) {
		
		this.packages = packages;
		this.finalPrices = finalPrices;
		this.insurerNames = insurerNames;
		this.idInsurer = idInsurer;
		
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
	public List<String> getInsurerNames() {
		return insurerNames;
	}
	public void setInsurerNames(List<String> insurerNames) {
		this.insurerNames = insurerNames;
	}
	public long[] getIdInsurer() {
		return idInsurer;
	}
	public void setIdInsurer(long[] idInsurer) {
		this.idInsurer = idInsurer;
	}
	
	
	
	
	
}
