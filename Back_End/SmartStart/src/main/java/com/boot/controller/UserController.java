package com.boot.controller;

import com.boot.exception.ResourceNotFoundException;
import com.boot.model.Home;
import com.boot.model.User;
import com.boot.payload.*;
import com.boot.repository.HomeRepository;
import com.boot.repository.UserRepository;
import com.boot.security.UserPrincipal;
import com.boot.security.CurrentUser;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
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
    	Home home = new Home(homeRequest.getMorada(),homeRequest.getArea(),
    			homeRequest.getAno(), homeRequest.getTopologia(), homeRequest.getCapitalImovel(), user);
    	
    	homeRepository.save(home);
    	
    	return ResponseEntity.ok().body(new ApiResponse(true, "User added to home successfully"));
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
    public ResponseEntity<Set<Home>> getHouses(@CurrentUser UserPrincipal currentUser){
    	User user = userRepository.findById(currentUser.getId())
    			.orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
    	Set<Home> homes = user.getHouses();
    	return ResponseEntity.ok().body(homes);
    }
    
}