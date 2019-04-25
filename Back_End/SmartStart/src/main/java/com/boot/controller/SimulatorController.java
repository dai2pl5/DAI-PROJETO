package com.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.boot.model.Coverage;
import com.boot.model.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.boot.payload.ApiResponse;
import com.boot.payload.HomeRequest;
import com.boot.payload.SimuladorRequest;
import com.boot.repository.CoverageRepository;
import com.boot.repository.PackageRepository;
import com.boot.model.Package;

@RestController
@RequestMapping("/simulador")
public class SimulatorController {
	
	@Autowired
	private CoverageRepository coverageRepository;
	@Autowired
	private PackageRepository packageRepository;
	
	@PostMapping("/execute")
	public ResponseEntity<List<Package>> execute(@RequestBody SimuladorRequest simuladorRequest){
		List<Package> pacotes = packageRepository.findAll();
		String[] names = simuladorRequest.getNames();
		List<Package> packagesSimulation = new ArrayList<>(); 
		for(int i = 0; i < pacotes.size(); i++) {
			Set<Coverage> coverages = pacotes.get(i).getCoverages();
			for(Coverage coverage: coverages) {
				System.out.println(coverage.getName());
				for(int b = 0; b < names.length; b++) {
					if(coverage.getName().equals(names[b])){
						packagesSimulation.add(pacotes.get(i));
					}
							
				}
				
			}
		}
		return ResponseEntity.ok().body(packagesSimulation);
	} 
}
