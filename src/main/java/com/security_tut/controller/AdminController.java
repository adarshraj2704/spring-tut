package com.security_tut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security_tut.dto.UserPersonalInfoGetDto;
import com.security_tut.serviceimpl.UserPersonalInfoServiceImpl;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private final UserPersonalInfoServiceImpl serviceImpl;
	
	@Autowired
	public AdminController(UserPersonalInfoServiceImpl serviceImpl) {
		super();
		this.serviceImpl = serviceImpl;
	}





	@GetMapping("/getall")
	public ResponseEntity<List<UserPersonalInfoGetDto>> getAllUsers()
	{
		
		List<UserPersonalInfoGetDto> users = serviceImpl.getAll();
		return ResponseEntity.ok(users);
		
		
	}
	
	

}
