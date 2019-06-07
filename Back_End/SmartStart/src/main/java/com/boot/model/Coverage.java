package com.boot.model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;




@Entity
@Table
public class Coverage {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCoverage;
	
	@NotBlank
	@Size(max=30)
	private String name;
	
	public Coverage() {}
	public Coverage(long idCoverage, String name) {
		
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
