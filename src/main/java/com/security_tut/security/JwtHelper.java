package com.security_tut.security;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtHelper {
	
	private static final long JWT_VALIDITY =  5 * 60 * 1000;
	
	private final String SECRECT = "huweqgdkzgiugisvibiubiBIBIWSIUBDVIVIUGUWBIEVVEAuyowekjbeywyvsduyvyavawsviyvwyeqyvvfhbkiyga";
	
	
	private Key key;
	
	@PostConstruct
	public void init()
	{
		this.key = Keys.hmacShaKeyFor(SECRECT.getBytes());
	}
	
	//creating token
	public String createToken(UserDetails userDetails)
	{
		return Jwts.builder()
				   .setSubject(userDetails.getUsername())
				   .setIssuedAt(new Date())
				   .setExpiration(new Date(System.currentTimeMillis()+JWT_VALIDITY))
				   .signWith(key,SignatureAlgorithm.HS256)
				   .compact();
	}
	
	//get username
	public String userNameFromToken(String token )
	{
		return getclaims(token).getSubject();
	}

	
	private Claims getclaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	//validate token
	
	public boolean isValidToken(String token, UserDetails userDetails)
	{
		String userName = userNameFromToken(token);
		
		return userName.equals(userDetails.getUsername())&& !isExpiration(token);
	}

	private boolean isExpiration(String token) {
		// TODO Auto-generated method stub
		return getclaims(token).getExpiration().before(new Date()) ;
	}

}
