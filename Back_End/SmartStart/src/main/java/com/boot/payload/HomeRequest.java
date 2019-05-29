package com.boot.payload;





public class HomeRequest {
	

	private long idHome;
	
	private String morada;
	
	private int area;
	
	private String ano;
	
	private double capitalImovel;
	
	private boolean owner;
	
	private double solarPanels;
	
	private boolean prevention;
	
	private String topologia;

	public HomeRequest() {}
	
	
	public HomeRequest(String morada, int area, String ano, double capitalImovel,
			boolean owner, double solarPanels, boolean prevention, String topologia) {
		
		this.morada = morada;
		this.area = area;
		this.ano = ano;
		this.capitalImovel = capitalImovel;
		this.owner = owner;
		this.solarPanels = solarPanels;
		this.prevention = prevention;
		this.topologia = topologia;

	}


	public long getIdHome() {
		return idHome;
	}


	public void setIdHome(long idHome) {
		this.idHome = idHome;
	}


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


	public String getAno() {
		return ano;
	}


	public void setAno(String ano) {
		this.ano = ano;
	}


	public double getCapitalImovel() {
		return capitalImovel;
	}


	public void setCapitalImovel(double capitalImovel) {
		this.capitalImovel = capitalImovel;
	}


	public boolean isOwner() {
		return owner;
	}


	public void setOwner(boolean owner) {
		this.owner = owner;
	}


	public double getSolarPanels() {
		return solarPanels;
	}


	public void setSolarPanels(double solarPanels) {
		this.solarPanels = solarPanels;
	}


	public boolean isPrevention() {
		return prevention;
	}


	public void setPrevention(boolean prevention) {
		this.prevention = prevention;
	}


	public String getTopologia() {
		return topologia;
	}


	public void setTopologia(String topologia) {
		this.topologia = topologia;
	}




	
	
	
	
}
