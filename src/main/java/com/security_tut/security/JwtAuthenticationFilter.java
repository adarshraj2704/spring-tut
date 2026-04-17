package com.security_tut.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final CustomUserDetailService customUserDetailService;
	
	
	private final JwtHelper jwtHelper;
	
	private final UserDetailsService detailsService;
	
	@Autowired
	public JwtAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService detailsService, CustomUserDetailService customUserDetailService) {
		super();
		this.jwtHelper = jwtHelper;
		this.detailsService = detailsService;
		this.customUserDetailService = customUserDetailService;
	}


//	 @Override
//	    protected boolean shouldNotFilter(HttpServletRequest request) {
//	        return request.getServletPath().equals("/auth/login");
//	    }
//	 	
	 
	 @Override
	 protected boolean shouldNotFilter(HttpServletRequest request) {
	     String path = request.getServletPath();
	     return path.startsWith("/auth")  || path.equals("/auth");
	 }


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 
		 String token = null;
		 String userName = null;
		 String autherizationHeader = request.getHeader("Authorization");
		 
		 
		 if(autherizationHeader != null && autherizationHeader.startsWith("Bearer"))
		 {
			 
			 try
			 {
				 
				 token = autherizationHeader.substring(7);
				 userName = jwtHelper.userNameFromToken(token);
				 
				 if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null)
				 {
					 UserDetails userDetails =  detailsService.loadUserByUsername(userName);
					 
					 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken
							 												 (userDetails,null,userDetails.getAuthorities());
					 
					 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					 SecurityContextHolder.getContext().setAuthentication(authentication);
				 }
				 
				 
			 }
			 catch (ExpiredJwtException e) {
				 System.out.println(e.getMessage());
			}
			 catch (MalformedJwtException e) {
				 System.out.println(e.getMessage());
			}
			 catch (IllegalArgumentException e) {
				 System.out.println(e.getMessage());
			}
			 catch(Exception e)
			 {
				 System.out.println(e.getMessage());
			 }
		 }
		 else
		 {
			System.out.println("invalid autherization header"); 
		 }
		 
		
		
		filterChain.doFilter(request, response);
		
	}
	
	
	

}
