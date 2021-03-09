package com.userservice.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.dto.UserDto;
import com.userservice.entity.User;
import com.userservice.exception.UserServiceException;
import com.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDto getUserById(Long id) {
		User user = Optional.of(userRepository.findById(id).get()).orElse(User.builder().build());
		return UserDto.builder().userId(user.getUserId()).firstName(user.getFirstName()).lastName(user.getLastName())
				.email(user.getEmail()).build();

	}

	public void registerUser(UserDto userDto) throws Exception{
		try {
			User user = User.builder().firstName(userDto.getFirstName()).lastName(userDto.getLastName())
					.email(userDto.getEmail()).dob(userDto.getDob()).doj(userDto.getDoj()).build();
			userRepository.save(user);
		} catch(Exception ex) {
			throw new UserServiceException(ex.getMessage());
		}
	}
	
//	public void validateUserDto(UserDto userDto) {
//		
//	}
//	
//	public static void main(String args[]) {
//		System.out.println("new Date = "+ new Date());
//	}

}
