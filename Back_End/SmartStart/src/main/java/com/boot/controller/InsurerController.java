package com.boot.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boot.security.UserPrincipal;
import com.boot.security.CurrentUser;
import com.boot.repository.ConfigurationRepository;
import com.boot.repository.CoverageRepository;
import com.boot.repository.InsuranceRepository;
import com.boot.repository.PackageRepository;
import com.boot.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import com.boot.model.Package;

import com.boot.exception.ResourceNotFoundException;
import com.boot.model.User;
import com.boot.model.Configuration;
import com.boot.model.Coverage;
import com.boot.model.Home;
import com.boot.model.Insurance;
import com.boot.payload.*;
import com.boot.projection.InsuranceAndClients;
import com.boot.projection.UserProjection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/insurer")
public class InsurerController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private CoverageRepository coverageRepository;
	
	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Autowired
	private InsuranceRepository insuranceRepository;
	
	@PostMapping("/addPackage")
    @PreAuthorize("hasRole('INSURER')")
	public ResponseEntity<ApiResponse> addPackage(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody PackageRequest packageRequest){
		String username = currentUser.getUsername();
		User user = userRepository.findByUsername(username)
    			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		Package package1 = new Package(packageRequest.getDescription(), packageRequest.getBasePrice(), user);
		packageRepository.save(package1);
		return ResponseEntity.ok().body(new ApiResponse(true, "Package added sucessfuly"));
	}
	
	@PutMapping("/addCoverage/{id}")
	@PreAuthorize("hasRole('INSURER')")
	public ResponseEntity<ApiResponse> addCobertura(@PathVariable(value = "id") Long id, @RequestBody CoverageRequest coverageRequest){
		Package package1 = packageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Package", "id", id));
		Set<Coverage> coverages = package1.getCoverages();
		System.out.println(coverageRequest.getName());
		String name = coverageRequest.getName();
		Coverage coverage = coverageRepository.findByName(name);
		System.out.println(coverage + "     " + package1);
		coverages.add(coverage);
		package1.setCoverages(coverages);
		packageRepository.save(package1);
		
		return ResponseEntity.ok().body(new ApiResponse(true, "Coverage added to package sucessfully"));
	}
	
	
	@PostMapping("/addConfig")
	@PreAuthorize("hasRole('INSURER')")
	public ResponseEntity<Configuration> addConfig(@RequestBody ConfigurationRequest configurationRequest, @CurrentUser UserPrincipal currentUser){
		User user = userRepository.findById(currentUser.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
		
		Configuration configuration = new Configuration(configurationRequest.getDesignation(), configurationRequest.getTax(), user);
		configurationRepository.save(configuration);
		return ResponseEntity.ok().body(configuration);
	}
	
	@PutMapping("/validateInsurance")
	@PreAuthorize("hasRole('INSURER')")
	public ResponseEntity<ApiResponse> validateInsurance(@RequestBody ValidateInsuranceRequest validateInsuranceRequest){
		
		long id = validateInsuranceRequest.getId();
		Insurance insurance = insuranceRepository.findById(id);
		
		insurance.setActive(true);
		insuranceRepository.save(insurance);
		
		return ResponseEntity.ok(new ApiResponse(true, "Seguro validado com sucesso!"));
	}
	
	@PutMapping("/rejectInsurance")
	@PreAuthorize("hasRole('INSURER')")
	public ResponseEntity<ApiResponse> rejectInsurance(@RequestBody ValidateInsuranceRequest validateInsuranceRequest){
		long id = validateInsuranceRequest.getId();
		Insurance insurance = insuranceRepository.findById(id);
		insurance.setRejected(true);
		insuranceRepository.save(insurance);
		
		return ResponseEntity.ok(new ApiResponse(true, "Seguro rejeitado!"));
	}
	
	@GetMapping("/getClients")
	@PreAuthorize("hasRole('INSURER')")
	public ResponseEntity<InsuranceAndClients> getClientes(@CurrentUser UserPrincipal currentUser){
		
		List<Insurance> insuranceList = insuranceRepository.findByUserId(currentUser.getId());
		List<UserProjection> clientsList = new ArrayList<UserProjection>();
		for(int i = 0; i < insuranceList.size(); i++) {
			Home home = insuranceList.get(i).getHome();
			User user = home.getUser();
			UserProjection userProjection = new UserProjection(user.getName(), user.getEmail());
			clientsList.add(userProjection);
		}
		
		InsuranceAndClients insuranceAndClients = new InsuranceAndClients(clientsList,insuranceList);
		
		
		return ResponseEntity.ok().body(insuranceAndClients);
		
	}
	
	@GetMapping("/getActiveInsurances")
	@PreAuthorize("hasRole('INSURER')")
	public ResponseEntity<InsuranceAndClients> getActiveInsurancesInsurer(@CurrentUser UserPrincipal currentUser){
		
		List<Insurance> insuranceList = insuranceRepository.findByUserId(currentUser.getId());
		List<Insurance> insuranceFinalList = new ArrayList<Insurance>();
		List<UserProjection> clientsList = new ArrayList<UserProjection>();
		for(Insurance insurance : insuranceList) {
			if(insurance.isActive() == true) {
			insuranceFinalList.add(insurance);
			Home home = insurance.getHome();
			User user = home.getUser();
			UserProjection userProjection = new UserProjection(user.getName(), user.getEmail());
			clientsList.add(userProjection);
			}
		}
		
		InsuranceAndClients insuranceAndClients = new InsuranceAndClients(clientsList,insuranceFinalList);
		
		
		return ResponseEntity.ok().body(insuranceAndClients);
	
		
	}
	
	@GetMapping("/getPackages")
	@PreAuthorize("hasRole('INSURER')")
	public ResponseEntity<List<Package>> getPackages(@CurrentUser UserPrincipal currentUser){
		User user = userRepository.findById(currentUser.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
		
		List<Package> packages = packageRepository.findByUser(user);
	
	return ResponseEntity.ok().body(packages);
	}
}
