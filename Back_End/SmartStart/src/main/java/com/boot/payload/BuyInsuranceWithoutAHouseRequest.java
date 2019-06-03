package com.boot.payload;

import com.boot.model.Home;
import com.boot.model.User;

public class BuyInsuranceWithoutAHouseRequest {
	
	private long idInsurance;
	
	private double price;
	
	private Home home;
	
	private User user;

	private boolean active;
	
	private boolean rejected;
	
	private long idHome;
	
	private String morada;
	
	private int area;
	
	private String ano;
	
	private double capitalImovel;
	
	private boolean owner;
	
	private double solarPanels;
	
	private boolean prevention;
	
	private String topologia;
	
	private long idPackage;
	private long idInsurer;
	
	public BuyInsuranceWithoutAHouseRequest() {}

	public BuyInsuranceWithoutAHouseRequest(double price, Home home, User user, boolean active, boolean rejected,
			String morada, int area, String ano, double capitalImovel, boolean owner, double solarPanels,
			boolean prevention, String topologia, long idPackage,long idInsurer) {
		this.price = price;
		this.home = home;
		this.user = user;
		this.active = active;
		this.rejected = rejected;
		this.morada = morada;
		this.area = area;
		this.ano = ano;
		this.capitalImovel = capitalImovel;
		this.owner = owner;
		this.solarPanels = solarPanels;
		this.prevention = prevention;
		this.topologia = topologia;
		this.idPackage = idPackage;
		this.idInsurer = idInsurer;
	}

	public long getIdInsurance() {
		return idInsurance;
	}

	public void setIdInsurance(long idInsurance) {
		this.idInsurance = idInsurance;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public long getIdPackage() {
		return idPackage;
	}

	public void setIdPackage(long idPackage) {
		this.idPackage = idPackage;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public long getIdInsurer() {
		return idInsurer;
	}

	public void setIdInsurer(long idInsurer) {
		this.idInsurer = idInsurer;
	}
	
	
	
	
	

}
