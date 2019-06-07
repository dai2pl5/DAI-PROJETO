package com.boot.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.exception.ResourceNotFoundException;
import com.boot.model.Role;
import com.boot.model.User;
import com.boot.payload.ApiResponse;
import com.boot.repository.RoleRepository;
import com.boot.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	  	@Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder passwordEncoder;
	    
	    @Autowired
	    UserRepository userRepository;
	    
	    @GetMapping("/getInsurers")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<List<User>> getInsurers(){
	    	
	    	List<User> users = userRepository.findAll();
	    	List<User> insurers = new ArrayList<User>();
	    	Role role = roleRepository.findById((long) 3).orElseThrow(() -> new ResourceNotFoundException("Role", "id", 3));	    
	    	
	    	for(User user : users) {
	    		Set<Role> roles = user.getRoles();
	    		if(roles.contains(role)) {
	    			insurers.add(user);
	    		}
	    	}
	    	
	    	return ResponseEntity.ok().body(insurers);
	    	
 	    
	    }
	    
	    @DeleteMapping("/deleteInsurer/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<ApiResponse> deleteInsurer(@PathVariable(value="id") long id){
	    	
	    	User user = userRepository.findById(id);
	    	userRepository.delete(user);
	    	return ResponseEntity.ok().body(new ApiResponse(true, "Seguradora eliminada com sucesso!"));
	    }
	    
}
