package com.boot.payload;


public class CoverageRequest {
	

	private long idCoverage;

	private String name;
	
	public CoverageRequest() {}
	public CoverageRequest(long idCoverage, String name) {
		
		this.idCoverage = idCoverage;
		this.name = name;
	}

	public long getIdCoverage() {
		return idCoverage;
	}

	public void setIdCoverage(long idCoverage) {
		this.idCoverage = idCoverage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
