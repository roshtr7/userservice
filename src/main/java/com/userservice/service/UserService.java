package com.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.dto.ResponseDto;
import com.userservice.dto.UserDto;
import com.userservice.entity.User;
import com.userservice.exception.UserServiceException;
import com.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDto getUserById(Long id) throws UserServiceException {
		User user = Optional.of(userRepository.findById(id).get())
				.orElseThrow(() -> new UserServiceException("user not found"));
		return UserDto.builder().id(user.getUserId()).firstName(user.getFirstName()).lastName(user.getLastName())
				.email(user.getEmail()).build();

	}

	public ResponseDto registerUser(UserDto userDto) {
		List<String> errorList = validateUser(userDto);
		if (errorList.size() > 0) {
			return ResponseDto.builder().errors(errorList).build();
		}
		User user = User.builder().firstName(userDto.getFirstName()).lastName(userDto.getLastName())
				.email(userDto.getEmail()).dob(userDto.getDob()).doj(userDto.getDoj()).pinCode(userDto.getPinCode())
				.isDelete(false).build();
		userRepository.save(user);
		return ResponseDto.builder().build();
	}

	public List<String> validateUser(UserDto user) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
		List<String> errorList = new ArrayList<>();
		for (ConstraintViolation<UserDto> violation : violations) {
			errorList.add(violation.getMessage());
		}
		return errorList;
	}

	public ResponseDto editUser(UserDto userDto) throws UserServiceException {
		List<String> errorList = validateUser(userDto);
		if (errorList.size() > 0) {
			return ResponseDto.builder().errors(errorList).build();
		}
		User user = Optional.of(userRepository.findById(userDto.getId()).get())
				.orElseThrow(() -> new UserServiceException("user not found"));
		mapUserDtoToUser(userDto, user);
		userRepository.save(user);
		return ResponseDto.builder().build();
	}

	private void mapUserDtoToUser(UserDto userDto, User user) {
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setDob(userDto.getDob());
		user.setDoj(userDto.getDoj());
		user.setPinCode(userDto.getPinCode());
		user.setDelete(false);
	}
}
