package com.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.dto.Role;
import com.ec.entity.UserEntity;
import com.ec.repository.UserRepository;
import com.ec.security.JwtUtil;

@RestController
@RequestMapping("/test")
public class TestControlle {
	
	 @GetMapping("/{token}")
	    public String test(@PathVariable String token) {

	        return JwtUtil.extractEmail(token);
	    }
	 @Autowired
	    private UserRepository repo;

	    @Autowired
	    private BCryptPasswordEncoder encoder;

	    @GetMapping("/test-save")
	    public String saveUser() {

	        UserEntity user = new UserEntity();

	        user.setName("Test User");
	        user.setEmail("test@gmail.com");
	        user.setPassword(
	                encoder.encode("12345"));

	        user.setRole(Role.ADMIN);

	        repo.save(user);

	        return "User Saved";
	    }
	 
	}

