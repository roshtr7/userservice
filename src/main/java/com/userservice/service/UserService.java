package com.userservice.service;

import java.util.List;

import com.userservice.dto.ResponseDto;
import com.userservice.dto.UserDto;
import com.userservice.entity.User;
import com.userservice.exception.UserServiceException;

public interface UserService {

	UserDto getUserById(Long id) throws UserServiceException;

	ResponseDto registerUser(UserDto userDto) throws UserServiceException;

	void validateEmail(String email) throws UserServiceException;

	List<String> validateUser(UserDto user);

	ResponseDto editUser(UserDto userDto) throws UserServiceException;

	void deleteUser(Long id) throws UserServiceException;

	void deleteUserFromDb(Long id) throws UserServiceException;

	List<UserDto> getUserList();

	List<User> searchUsersByFilter(String firstName, String lastName, String pinCode);

}