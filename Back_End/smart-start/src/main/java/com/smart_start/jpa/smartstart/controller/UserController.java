package com.smart_start.jpa.smartstart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_start.jpa.smartstart.model.Users;
import com.smart_start.jpa.smartstart.repository.UsersRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UsersRepository usersRepository;
	
	@GetMapping("/all")
	public List<Users> getAll(){
		return usersRepository.findAll();
	}
	
	@PostMapping("/add")
	public Users addUser(Users user) {
		usersRepository.save(user);
		return user;
	}
}
