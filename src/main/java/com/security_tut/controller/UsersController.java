package com.security_tut.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security_tut.dto.UserPersonalInfoGetDto;
import com.security_tut.dto.UserPersonalInfoSetDto;
import com.security_tut.serviceimpl.UserPersonalInfoServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsersController {
	
	private final UserPersonalInfoServiceImpl impl;

	public UsersController(UserPersonalInfoServiceImpl impl) {
		super();
		this.impl = impl;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> addUser(@Valid @RequestBody UserPersonalInfoSetDto setDto)
	{
	  	
		return ResponseEntity.ok(impl.addUser(setDto));
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<UserPersonalInfoGetDto> getUser(@PathVariable Long id)
	{
		return ResponseEntity.ok(impl.getUser(id));
	}
	
	

}
