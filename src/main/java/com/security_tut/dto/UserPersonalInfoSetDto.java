package com.security_tut.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPersonalInfoSetDto {
	
	
	private Long id;
	
	@NotNull
	private String name;
	
	private Integer age;
	
	private java.util.Date dob;
	
	@NotNull
	private String email;

}
