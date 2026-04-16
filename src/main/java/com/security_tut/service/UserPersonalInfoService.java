package com.security_tut.service;

import java.util.List;


import com.security_tut.dto.UserPersonalInfoGetDto;
import com.security_tut.dto.UserPersonalInfoSetDto;

public interface UserPersonalInfoService {
	
	public List<UserPersonalInfoGetDto> getAll();
	
	public String addUser(UserPersonalInfoSetDto setDto);
	
	public UserPersonalInfoGetDto getUser(Long id);

}
