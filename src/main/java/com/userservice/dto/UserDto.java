package com.userservice.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	
	private Long userId;
	
	@NotBlank(message = "First name is mandatory")
	private String firstName;
	
	@NotBlank(message = "Last name is mandatory")
	private String lastName;
	
	@Email(message = "Enter a valid email")
	private String email;
	
	@NotNull(message = "DOB name is mandatory")
	private Date dob;
	
	@NotNull(message = "DOJ name is mandatory")
	private Date doj;
	
	@NotNull(message = "Pin code name is mandatory")
	private Long pinCode;
	
}
