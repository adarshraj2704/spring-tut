package com.security_tut.serviceimpl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.security_tut.dto.UserPersonalInfoGetDto;
import com.security_tut.dto.UserPersonalInfoSetDto;
import com.security_tut.entity.UsersPersonalInfo;
import com.security_tut.repository.userPersonalInfoRepo;
import com.security_tut.service.UserPersonalInfoService;

@Service
public class UserPersonalInfoServiceImpl implements UserPersonalInfoService{
	
	private final	userPersonalInfoRepo infoRepo;
	
	

	public UserPersonalInfoServiceImpl(userPersonalInfoRepo infoRepo) {
		super();
		this.infoRepo = infoRepo;
	}



	@Override
	public List<UserPersonalInfoGetDto> getAll() {
		
		List<UsersPersonalInfo> users = infoRepo.findAll();
		
		return users.stream().map(user ->new  UserPersonalInfoGetDto(
				
										user.getName(),
										user.getAge(),
										user.getDob(),
										user.getEmail()
											)).toList();
	}



	@Override
	public String addUser(UserPersonalInfoSetDto setDto) {
		
		UsersPersonalInfo info = new  UsersPersonalInfo(
				
				setDto.getId(),
				setDto.getName(),
				setDto.getAge(),
				setDto.getDob(),
				setDto.getEmail()
				);
		
		infoRepo.save(info);
		
		return "user added";
	}



	@Override
	public UserPersonalInfoGetDto getUser(Long id) {
		
		UsersPersonalInfo user = infoRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("id not found!!"));
		
		
		return new UserPersonalInfoGetDto(
				
				user.getName(),
				user.getAge(),
				user.getDob(),
				user.getEmail()
				);
	}





}
