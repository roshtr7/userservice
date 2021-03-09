package com.userservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long userId;

	@NotBlank(message = "First name is mandatory")
	@Column(nullable = false)
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	@Column(nullable = false)
	private String lastName;

	@Email(message = "Enter a valid email")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "DOB is mandatory")
	@Column(nullable = false)
	private Date dob;

	@NotNull(message = "DOJ is mandatory")
	@Column(nullable = false)
	private Date doj;

	@NotNull(message = "Pin code is mandatory")
	@Column(nullable = false)
	private Long pinCode;
	
	@NotNull
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isDelete = false;
}
