package com.boot.controller;

import com.boot.exception.ResourceNotFoundException;
import com.boot.model.Home;
import com.boot.model.User;
import com.boot.payload.*;
import com.boot.projection.HousesAndInsurancesProjection;
import com.boot.repository.HomeRepository;
import com.boot.repository.InsuranceRepository;
import com.boot.repository.UserRepository;
import com.boot.security.UserPrincipal;
import com.boot.security.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Set;
import com.boot.model.Insurance;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private HomeRepository homeRepository;
    
    @Autowired
    private InsuranceRepository insuranceRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    public ResponseEntity<User> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
    	User user = userRepository.findById(currentUser.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
    	
    	
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

   /* @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return ResponseEntity.ok().body(user);
    }*/
    
    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));


        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());

        return userProfile;
    }
    
    @PostMapping("/user/addHome")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse> userAddHome(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody HomeRequest homeRequest){
    	String username = currentUser.getUsername();
    	User user = userRepository.findByUsername(username)
    			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    	Home home = new Home(homeRequest.getMorada(), homeRequest.getArea(), homeRequest.getAno(), homeRequest.getCapitalImovel(), homeRequest.isOwner(), homeRequest.getSolarPanels(), homeRequest.isPrevention(), homeRequest.getTopologia(), user);
    	
    	homeRepository.save(home);
    	
    	return ResponseEntity.ok().body(new ApiResponse(true, "Casa adiconada com sucesso!"));
    }
    
    @PutMapping("/user/alterHome/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Home> alterHome(@PathVariable(value="id") long id, @RequestBody HomeRequest homeRequest){
    	
    	Home home = homeRepository.findById(id);
    	home.setAno(homeRequest.getAno());
    	home.setArea(homeRequest.getArea());
    	home.setCapitalImovel(homeRequest.getCapitalImovel());
    	home.setMorada(homeRequest.getMorada());
    	home.setTopologia(homeRequest.getTopologia());
    	home.setOwner(homeRequest.isOwner());
    	home.setSolarPanels(homeRequest.getSolarPanels());
    	home.setPrevention(homeRequest.isPrevention());
    	
    	homeRepository.save(home);
    	
    	return ResponseEntity.ok().body(home);
    }
    
    @DeleteMapping("/user/deleteHome/{id}")
    @PreAuthorize("hasRole('USER')")
	public ResponseEntity<ApiResponse> deleteHome(@PathVariable(value="id") long id){
	    Home home = homeRepository.findById(id);
	    homeRepository.delete(home);
	    return ResponseEntity.ok(new ApiResponse(true, "Home deleted sucessfully"));
	}
    
    @GetMapping("/user/getHouses")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HousesAndInsurancesProjection> getHouses(@CurrentUser UserPrincipal currentUser){
    	User user = userRepository.findById(currentUser.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
    	Set<Home> homes = user.getHouses();
    	Boolean[] isInsured = new Boolean[homes.size()];
    	int index = 0;
    	for(Home home : homes) {
    		Insurance insurance = insuranceRepository.findByHome(home);
    		
    		if(insurance != null) {
    			System.out.println(insurance.getPrice());
    			isInsured[index] = true;
    			index += 1;
    		}else {
    			isInsured[index] = false;
    			index += 1;
    		}
    	}
    	
    	HousesAndInsurancesProjection housesAndInsurancesProjection = new HousesAndInsurancesProjection(homes,isInsured);
    	return ResponseEntity.ok().body(housesAndInsurancesProjection);
    }
    
    @PutMapping("/user/updateInfo")
    public ResponseEntity<?> updateInfo(@CurrentUser UserPrincipal currentUser, @RequestBody UserPayload userPayload){
    	User user = userRepository.findById(currentUser.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));


         
         user.setUsername(userPayload.getUsername());
         user.setEmail(userPayload.getEmail());
         user.setPassword(userPayload.getPassword());
         user.setName(userPayload.getName());

         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepository.save(user);

    	return ResponseEntity.ok(new ApiResponse(true,"Informação atualizada com sucesso!"));
    }
    
    @PostMapping("/user/isHouseInsured")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> isHouseInsured(@RequestBody HomeRequest homeRequest){
    	Home home = homeRepository.findById(homeRequest.getIdHome());
    	System.out.println(homeRequest.getIdHome());
    	Insurance insurance = insuranceRepository.findByHome(home);
	    if(insurance != null) {
	    	return ResponseEntity.ok().body(true);
	    }else {
	    	return ResponseEntity.ok().body(false);
	    }
    }
}