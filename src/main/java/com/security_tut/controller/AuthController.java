package com.security_tut.controller;

import java.time.DateTimeException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security_tut.dto.JwtResponse;
import com.security_tut.dto.LoginRequest;
import com.security_tut.exception.ErrorResponse;
import com.security_tut.security.JwtHelper;



@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	private AuthenticationManager authenticationManager;
	
	private JwtHelper jwtHelper;
	
	private UserDetailsService userDetailsService;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtHelper jwtHelper,
			UserDetailsService userDetailsService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtHelper = jwtHelper;
		this.userDetailsService = userDetailsService;
	}




	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody LoginRequest loginRequest)
	{
		
		try 
		{
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());
			
			this.authenticationManager.authenticate(authentication);
			
			UserDetails userDetails =  userDetailsService.loadUserByUsername(loginRequest.getUserName());
			String token = jwtHelper.createToken(userDetails);
			
			JwtResponse jwtResponse = new JwtResponse(token,userDetails.getUsername());
			
			return ResponseEntity.ok(jwtResponse);
		}
		catch(BadCredentialsException e)
		{
			System.out.println(e.getMessage());
			
			ErrorResponse errorResponse = new ErrorResponse("username & password incorect",403, false);
			
//			return ResponseEntity.status(401).body(null);
			return ResponseEntity.status(401).body(errorResponse);
		}
		
		
	}

}
