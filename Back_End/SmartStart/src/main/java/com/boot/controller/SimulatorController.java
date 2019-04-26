package com.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.boot.exception.ResourceNotFoundException;
import com.boot.model.Home;
import com.boot.model.Package;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.boot.payload.FinalSimulatorRequest;
import com.boot.payload.SimuladorRequest;
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
	public ResponseEntity<FinalSimulatorRequest> execute(@PathVariable(value = "id") Long id, @CurrentUser UserPrincipal currentUser, @RequestBody SimuladorRequest simuladorRequest){
		List<Package> packages = packageRepository.findAll();
		String[] names = simuladorRequest.getNames();
		Home home = homeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Home", "id", id));
		List<Package> packagesSimulation = simulatorService.returnPackages(packages, names); 
		Double[] finalPrices = simulatorService.finalPrice(packagesSimulation, home);
		
		FinalSimulatorRequest finalSimulatorRequest = new FinalSimulatorRequest();
		finalSimulatorRequest.setPackages(packagesSimulation);
		finalSimulatorRequest.setFinalPrices(finalPrices);
		return ResponseEntity.ok().body(finalSimulatorRequest);
	}
	
	
}
