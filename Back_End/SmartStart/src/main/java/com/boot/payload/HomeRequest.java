package com.boot.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class HomeRequest {
	
	@NotBlank
	@Size(max=50)
	private String morada;
	
	private int area;
	
	private int ano;
	
	@NotBlank
	@Size(max=2)
	private String topologia;

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTopologia() {
		return topologia;
	}

	public void setTopologia(String topologia) {
		this.topologia = topologia;
	}
	
}
