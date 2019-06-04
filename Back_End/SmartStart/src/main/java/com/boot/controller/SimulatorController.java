package com.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.boot.exception.ResourceNotFoundException;
import com.boot.model.Home;
import com.boot.model.Package;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.boot.payload.FinalSimulatorRequest;
import com.boot.payload.HomeRequest;
import com.boot.payload.SimuladorRequest;
import com.boot.payload.SimulatorRequest2;
import com.boot.repository.HomeRepository;
import com.boot.repository.PackageRepository;
import com.boot.security.CurrentUser;
import com.boot.security.UserPrincipal;
import com.boot.service.SimulatorService;


@RestController
@RequestMapping("/simulator")
public class SimulatorController {
	
	@Autowired
	private PackageRepository packageRepository;
	@Autowired
	private SimulatorService simulatorService;
	@Autowired
	private HomeRepository homeRepository;
	
	
	@PostMapping("/execute/{id}")
	public ResponseEntity<FinalSimulatorRequest> execute(@PathVariable(value = "id") Long id, @CurrentUser UserPrincipal currentUser, @RequestBody SimuladorRequest simulatorRequest){
		List<Package> packages = packageRepository.findAll();
		List<String> insurerNames = new ArrayList<String>();
		Home home = homeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("House", "id", id));
		String[] names = simulatorRequest.getNames();
		List<Package> packagesSimulation = simulatorService.returnPackages(packages, names); 
		Double[] finalPrices = simulatorService.finalPrice(packagesSimulation, home);
		
		long[] idInsurers = new long[packagesSimulation.size()];
		int index = 0;
		for(Package packageAfter : packagesSimulation) {
			idInsurers[index] = packageAfter.getUser().getId();
			insurerNames.add(packageAfter.getUser().getName());
			index += 1;
		}
		FinalSimulatorRequest finalSimulatorRequest = new FinalSimulatorRequest();
		finalSimulatorRequest.setPackages(packagesSimulation);
		finalSimulatorRequest.setFinalPrices(finalPrices);
		finalSimulatorRequest.setIdInsurer(idInsurers);
		finalSimulatorRequest.setInsurerNames(insurerNames);
		return ResponseEntity.ok().body(finalSimulatorRequest);
	}
	
	@PostMapping("/execute/noDeal")
	public ResponseEntity<FinalSimulatorRequest> executeWithoutHome(@CurrentUser UserPrincipal currentUser, @RequestBody SimulatorRequest2 simulatorRequest){
		List<Package> packages = packageRepository.findAll();
		List<String> insurerNames = new ArrayList<String>();
		String morada = simulatorRequest.getMorada();
		int area = simulatorRequest.getArea();
		String ano = simulatorRequest.getAno();
		double capitalImovel = simulatorRequest.getCapitalImovel();
		boolean owner = simulatorRequest.isOwner();
		double solarPanels = simulatorRequest.getSolarPanels();
		boolean prevention = simulatorRequest.isPrevention();
		String topologia = simulatorRequest.getTopologia();
		HomeRequest homeRequest = new HomeRequest(morada,area,ano,capitalImovel, owner, solarPanels, prevention, topologia);
		String[] names = simulatorRequest.getNames();
		List<Package> packagesSimulation = simulatorService.returnPackages(packages, names); 
		Double[] finalPrices = simulatorService.finalPrice2(packagesSimulation, homeRequest);
		
		long[] idInsurers = new long[packagesSimulation.size()];
		int index = 0;
		for(Package packageAfter : packagesSimulation) {
			idInsurers[index] = packageAfter.getUser().getId();
			insurerNames.add(packageAfter.getUser().getName());
			index += 1;
		}
		FinalSimulatorRequest finalSimulatorRequest = new FinalSimulatorRequest();
		finalSimulatorRequest.setPackages(packagesSimulation);
		finalSimulatorRequest.setFinalPrices(finalPrices);
		finalSimulatorRequest.setIdInsurer(idInsurers);
		finalSimulatorRequest.setInsurerNames(insurerNames);
		
		return ResponseEntity.ok().body(finalSimulatorRequest);
	}
	
	
}
