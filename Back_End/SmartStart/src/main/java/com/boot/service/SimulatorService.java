package com.boot.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.boot.model.Configuration;
import com.boot.model.Coverage;
import com.boot.model.Home;
import com.boot.model.Package;
import com.boot.model.User;
import com.boot.payload.HomeRequest;
@Service
public class SimulatorService {
	private List<Package> packagesSimulation;
	
	public List<Package> returnPackages(List<Package> packages, String[] names){
		this.packagesSimulation = new ArrayList<>();
		for(int i = 0; i < packages.size(); i++) {
			Set<Coverage> coverages = packages.get(i).getCoverages();
			for(Coverage coverage: coverages) {
				System.out.println(coverage.getName());
				for(int b = 0; b < names.length; b++) {
					if(coverage.getName().equals(names[b])){
						if(!packagesSimulation.contains(packages.get(i))) {
							packagesSimulation.add(packages.get(i));
						}
					}		
				}
			}
		}
		return packagesSimulation;
	}
	
	public Double[] finalPrice(List<Package> packagesSimulation, Home home) {
		
		
		double area = home.getArea();
		double capital = home.getCapitalImovel();
		boolean prevention = home.isPrevention();
		Double[] finalPrices = new Double[packagesSimulation.size()];
		System.out.println(finalPrices.length);
		int index = 0;
		for(Package package1 : packagesSimulation) {
			User user = package1.getUser();
			double finalPrice = package1.getBasePrice();
			double basePrice = package1.getBasePrice();
			int indexFinal = 0;
			System.out.println(finalPrice);
			Set<Configuration> configurations = user.getConfigurations();
			for(Configuration configuration : configurations) {
				String designation = configuration.getDesignation();
				double tax = configuration.getTax();
				if(designation.equals("capitalImovel")) {
					finalPrice += capital*tax;
					indexFinal +=1;
					if(configurations.size() == indexFinal) {
						finalPrices[index] = finalPrice;
						}
				}else if(designation.equals("areaImovel")) {
						finalPrice += area * tax;
						indexFinal +=1;
						if(configurations.size() == indexFinal) {
							finalPrices[index] = finalPrice; 
					}
				}else if(designation.equals("preventionTax")) {
					if(prevention != true) {
						finalPrice += basePrice * tax;
						indexFinal +=1;
					}
					indexFinal +=1;
					if(configurations.size() == indexFinal) {
						finalPrices[index] = finalPrice; 
					}
				}
			}
			index +=1;
		}
		return finalPrices;
	}
	
public Double[] finalPrice2(List<Package> packagesSimulation, HomeRequest homeRequest) {
		
		
		double area = homeRequest.getArea();
		double capital = homeRequest.getCapitalImovel();
		boolean prevention = homeRequest.isPrevention();
		boolean owner = homeRequest.isOwner();
		double solarPanels = homeRequest.getSolarPanels();
		Double[] finalPrices = new Double[packagesSimulation.size()];
		
		int index = 0;
		for(Package package1 : packagesSimulation) {
			User user = package1.getUser();
			double finalPrice = package1.getBasePrice();
			double basePrice = package1.getBasePrice();
			int indexFinal = 0;
			System.out.println(finalPrice);
			
			Set<Configuration> configurations = user.getConfigurations();
			for(Configuration configuration : configurations) {
				String designation = configuration.getDesignation();
				double tax = configuration.getTax();
				System.out.println("Tamanho da lista de configs " + configurations.size());
				if(designation.equals("capitalImovel")) {
					finalPrice = finalPrice + (capital*tax);
					indexFinal +=1;
					if(configurations.size() == indexFinal) {
						finalPrices[index] = finalPrice;
					}
				}else if(designation.equals("areaImovel")) {
						finalPrice += area * tax;
						indexFinal +=1;
						
						if(configurations.size() == indexFinal) {
							finalPrices[index] = finalPrice; 
					}
				}else if(designation.equals("preventionTax")) {
					if(prevention != true) {
						finalPrice += basePrice * tax;
						indexFinal +=1;
					}else {
						indexFinal +=1;
					}
					if(configurations.size() == indexFinal) {
						finalPrices[index] = finalPrice; 
					}
				}else if(designation.equals("inquilinoTax")) {
					if(owner == false) {
					finalPrice += basePrice * tax;
					indexFinal +=1;
					}else {
						
						indexFinal += 1;
					}
					if(configurations.size() == indexFinal) {
						finalPrices[index] = finalPrice; 
					}
				}else if(designation.equals("ownerTax")) {
					
					if(owner == true) {
					finalPrice += basePrice * tax;
					indexFinal +=1;
					}else {
						indexFinal += 1;
					}
					if(configurations.size() == indexFinal) {
						finalPrices[index] = finalPrice; 
					}
				}else if(designation.equals("solarPanelsTax")) {
					
					if(solarPanels != 0) {
					finalPrice += solarPanels * tax;
					indexFinal +=1;
					}else {
						indexFinal += 1;
					}
					if(configurations.size() == indexFinal) {
						finalPrices[index] = finalPrice; 
					}
				}
				
			}
			index +=1;
		}
		return finalPrices;
	}
}
