package com.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.payload.BuyInsuranceRequest;
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
import com.boot.projection.InsuranceAndClients;
import com.boot.projection.InsurancesAndInsurerPayload;
import com.boot.projection.UserProjection;

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
	
	
	@PostMapping("/buyInsuranceWithoutAHouse")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Insurance> buyInsuranceWithoutAHouse(@CurrentUser UserPrincipal currentUser, @RequestBody BuyInsuranceWithoutAHouseRequest buyInsuranceWithoutAHouseRequest){
		String username = currentUser.getUsername();
		User user = userRepository.findByUsername(username)
    			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		Home home = new Home(buyInsuranceWithoutAHouseRequest.getMorada(), buyInsuranceWithoutAHouseRequest.getArea(), buyInsuranceWithoutAHouseRequest.getAno(), buyInsuranceWithoutAHouseRequest.getCapitalImovel(), buyInsuranceWithoutAHouseRequest.isOwner(), buyInsuranceWithoutAHouseRequest.getSolarPanels(), buyInsuranceWithoutAHouseRequest.isPrevention(), buyInsuranceWithoutAHouseRequest.getTopologia(), user);
		homeRepository.save(home);
		long id = buyInsuranceWithoutAHouseRequest.getIdInsurer();
		User userInsurer = userRepository.findById(id);
		Package packageInsurer = packageRepository.findById(buyInsuranceWithoutAHouseRequest.getIdPackage());
		Insurance insurance = new Insurance(buyInsuranceWithoutAHouseRequest.getPrice(),home,userInsurer, false, false,packageInsurer);
		insuranceRepository.save(insurance);
		return ResponseEntity.ok().body(insurance);
		
	}
	
	@PostMapping("/buyInsurance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Insurance> buyInsurance(@CurrentUser UserPrincipal currentUser, @RequestBody BuyInsuranceRequest buyInsuranceRequest){
		String username = currentUser.getUsername();
		User user = userRepository.findByUsername(username)
    			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		Home home = homeRepository.findById(buyInsuranceRequest.getIdHome());
		long idInsurer = buyInsuranceRequest.getIdInsurer();
		User userInsurer = userRepository.findById(idInsurer);
		Package packageInsurer = packageRepository.findById(buyInsuranceRequest.getIdPackage());
		Insurance insurance = new Insurance(buyInsuranceRequest.getPrice(),home,userInsurer, false, false,packageInsurer);
		insuranceRepository.save(insurance);
		return ResponseEntity.ok().body(insurance);
		
	}
	
	@GetMapping("/getInsurances")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<InsurancesAndInsurerPayload> getInsurances(@CurrentUser UserPrincipal currentUser){
		String username = currentUser.getUsername();
		User user = userRepository.findByUsername(username)
    			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		
		List<Insurance> userInsurances = new ArrayList<Insurance>();
		List<String> insurerNames = new ArrayList<String>();
		Set<Home> houses = user.getHouses();
		User userList;
		String name;
		for(Home house : houses) {
			Insurance insurance = insuranceRepository.findByHome(house);
			if(insurance != null) {
			userInsurances.add(insurance);
			userList = insurance.getUser();
			name = userList.getName();
			insurerNames.add(name);
			}
		}
		
		InsurancesAndInsurerPayload insuranceAndInsurer = new InsurancesAndInsurerPayload(userInsurances, insurerNames);
		
		return ResponseEntity.ok().body(insuranceAndInsurer);
		
	}
	
		@GetMapping("/getActiveInsurances")
		@PreAuthorize("hasRole('USER')")
		public ResponseEntity<InsurancesAndInsurerPayload> getActiveInsurances(@CurrentUser UserPrincipal currentUser){
			
			String username = currentUser.getUsername();
			User user = userRepository.findByUsername(username)
	    			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
			
			List<Insurance> userInsurances = new ArrayList<Insurance>();
			List<String> insurerNames = new ArrayList<String>();
			Set<Home> houses = user.getHouses();
			User userList;
			String name;
			for(Home house : houses) {
				Insurance insurance = insuranceRepository.findByHome(house);
				if(insurance != null && insurance.isActive() == true) {
				userInsurances.add(insurance);
				userList = insurance.getUser();
				name = userList.getName();
				insurerNames.add(name);
				}
			}
			
			InsurancesAndInsurerPayload insuranceAndInsurer = new InsurancesAndInsurerPayload(userInsurances, insurerNames);
			
			return ResponseEntity.ok().body(insuranceAndInsurer);
			
		}
		
		
	
}
