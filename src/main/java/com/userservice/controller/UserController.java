package com.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.UserDto;
import com.userservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getUserById(Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@PostMapping("/user")
	public ResponseEntity<Void> registerUser(@RequestBody @Valid UserDto userDto) throws Exception {
		userService.registerUser(userDto);
		return ResponseEntity.ok().build();
	}
	
}
