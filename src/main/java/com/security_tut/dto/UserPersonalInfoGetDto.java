package com.security_tut.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPersonalInfoGetDto {

		
		private String name;
		
		private Integer age;
		
		private java.util.Date dob;
		
		private String email;


}
