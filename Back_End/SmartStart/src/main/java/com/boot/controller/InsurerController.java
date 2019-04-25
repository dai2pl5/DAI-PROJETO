package com.boot.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boot.security.UserPrincipal;
import com.boot.security.CurrentUser;
import com.boot.repository.CoverageRepository;
import com.boot.repository.PackageRepository;
import com.boot.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import com.boot.model.Package;

import com.boot.exception.ResourceNotFoundException;
import com.boot.model.User;
import com.boot.model.Coverage;
import com.boot.payload.*;
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
	
	
}
