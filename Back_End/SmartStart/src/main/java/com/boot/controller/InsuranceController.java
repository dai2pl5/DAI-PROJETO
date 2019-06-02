package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boot.payload.BuyInsuranceWithoutAHouseRequest;
import com.boot.exception.ResourceNotFoundException;
import com.boot.model.Home;
import com.boot.model.Insurance;
import com.boot.model.User;
import com.boot.model.Package;
import com.boot.repository.HomeRepository;
import com.boot.repository.InsuranceRepository;
import com.boot.repository.PackageRepository;
import com.boot.repository.UserRepository;
import com.boot.security.CurrentUser;
import com.boot.security.UserPrincipal;

@RestController
@RequestMapping("/user/insurance")
public class InsuranceController {

	@Autowired
	private InsuranceRepository insuranceRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HomeRepository homeRepository;
	@Autowired
	private PackageRepository packageRepository;
	
	
	@PostMapping("/buyInsurance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Insurance> buyInsurance(@CurrentUser UserPrincipal currentUser, @RequestBody BuyInsuranceWithoutAHouseRequest buyInsuranceWithoutAHouseRequest){
		String username = currentUser.getUsername();
		User user = userRepository.findByUsername(username)
    			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		Home home = new Home(buyInsuranceWithoutAHouseRequest.getMorada(), buyInsuranceWithoutAHouseRequest.getArea(), buyInsuranceWithoutAHouseRequest.getAno(), buyInsuranceWithoutAHouseRequest.getCapitalImovel(), buyInsuranceWithoutAHouseRequest.isOwner(), buyInsuranceWithoutAHouseRequest.getSolarPanels(), buyInsuranceWithoutAHouseRequest.isPrevention(), buyInsuranceWithoutAHouseRequest.getTopologia(), user);
		homeRepository.save(home);
		int id = 2;
		User userInsurer = userRepository.findById(id);
		Package packageInsurer = packageRepository.findById(buyInsuranceWithoutAHouseRequest.getIdPackage());
		Insurance insurance = new Insurance(buyInsuranceWithoutAHouseRequest.getPrice(),home,userInsurer, false, false,packageInsurer);
		insuranceRepository.save(insurance);
		return ResponseEntity.ok().body(insurance);
		
	}
	
}
