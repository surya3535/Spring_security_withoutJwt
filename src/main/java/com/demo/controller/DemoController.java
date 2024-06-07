package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.UserCustom;
import com.demo.repository.UserRepository;

@RestController
public class DemoController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/home")
	public String home() {
		return "Home_Page";
	}
	
	@GetMapping("/user")
	public String user() {
		return "User_Page";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "Admin_Page";
	}
	
	@PostMapping("/save")
	public UserCustom saveUser(@RequestBody UserCustom user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}

}
