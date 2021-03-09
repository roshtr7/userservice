package com.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.ResponseDto;
import com.userservice.dto.UserDto;
import com.userservice.exception.UserServiceException;
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
	public ResponseEntity<UserDto> getUserById(Long id) throws UserServiceException {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@PostMapping("/user")
	public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto) throws UserServiceException {
		ResponseDto responseDto = userService.registerUser(userDto);
		if (responseDto.getErrors() != null) {
			return ResponseEntity.badRequest().body(responseDto);
		}
		return ResponseEntity.ok(responseDto);
	}

	@PutMapping("/user")
	public ResponseEntity<ResponseDto> editUser(@RequestBody UserDto userDto) throws UserServiceException {
		ResponseDto responseDto = userService.editUser(userDto);
		if (responseDto.getErrors() != null) {
			return ResponseEntity.badRequest().body(responseDto);
		}
		return ResponseEntity.ok(responseDto);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long id) throws UserServiceException {
		userService.deleteUser(id);
		return ResponseEntity.ok(ResponseDto.builder().build());
	}

}
