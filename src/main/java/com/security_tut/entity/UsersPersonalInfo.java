package com.security_tut.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_personal_info")
@Entity
public class UsersPersonalInfo {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long id;
	
	@NotNull
	private String name;
	
	private Integer age;
	
	private java.util.Date dob;
	
	@NotNull
	private String email;

}
