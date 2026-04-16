package com.security_tut.security;

import javax.management.relation.Role;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		UserDetails user = org.springframework.security.core.userdetails.User.builder()
								.username("adarsh27")
								.password("{noop}user123")
								.roles("user")
								.build();
		return user;
	}

}
