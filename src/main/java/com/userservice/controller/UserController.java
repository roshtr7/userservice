package com.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.ResponseDto;
import com.userservice.dto.UserDto;
import com.userservice.exception.UserServiceException;
import com.userservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getUserById(Long id) throws UserServiceException {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@GetMapping("/user/list")
	public ResponseEntity<List<UserDto>> getUserList() {
		return ResponseEntity.ok(userService.getUserList());
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

	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<ResponseDto> deleteUserFromDb(@PathVariable Long id) throws UserServiceException {
		userService.deleteUserFromDb(id);
		return ResponseEntity.ok(ResponseDto.builder().build());
	}

	@GetMapping("/user/search")
	public ResponseEntity<ResponseDto> searchUser(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "pinCode", required = false) String pinCode) {
		return ResponseEntity
				.ok(ResponseDto.builder().data(userService.searchUsersByFilter(firstName, lastName, pinCode)).build());
	}
}
