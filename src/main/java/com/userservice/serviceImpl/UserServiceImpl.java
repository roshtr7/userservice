package com.userservice.serviceImpl;

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
import com.userservice.service.UserService;
import com.userservice.util.AppConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto getUserById(Long id) throws UserServiceException {
		User user = Optional.ofNullable(userRepository.findByIdAndIsDelete(id, false))
				.orElseThrow(() -> new UserServiceException(AppConstants.ErrorMsgs.USER_NOT_FOUND));
		return UserDto.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName())
				.email(user.getEmail()).build();

	}

	@Override
	public ResponseDto registerUser(UserDto userDto) throws UserServiceException {
		List<String> errorList = validateUser(userDto);
		if (errorList.size() > 0) {
			return ResponseDto.builder().errors(errorList).build();
		}
		validateEmail(userDto.getEmail());
		User user = User.builder().firstName(userDto.getFirstName()).lastName(userDto.getLastName())
				.email(userDto.getEmail()).dateOfBirth(userDto.getDob()).dateOfJoining(userDto.getDoj())
				.pinCode(userDto.getPinCode()).build();
		userRepository.save(user);
		return ResponseDto.builder().build();
	}

	@Override
	public void validateEmail(String email) throws UserServiceException {
		Optional<Long> userId = userRepository.findByEmail(email);
		if (userId.isPresent()) {
			throw new UserServiceException(AppConstants.ErrorMsgs.DUPLICATE_EMAIL);
		}
	}

	@Override
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

	@Override
	public ResponseDto editUser(UserDto userDto) throws UserServiceException {
		List<String> errorList = validateUser(userDto);
		if (errorList.size() > 0) {
			return ResponseDto.builder().errors(errorList).build();
		}
		User user = Optional.ofNullable(userRepository.findByIdAndIsDelete(userDto.getId(), false))
				.orElseThrow(() -> new UserServiceException(AppConstants.ErrorMsgs.USER_NOT_FOUND));
		mapUserDtoToUser(userDto, user);
		userRepository.save(user);
		return ResponseDto.builder().build();
	}

	private void mapUserDtoToUser(UserDto userDto, User user) {
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setDateOfBirth(userDto.getDob());
		user.setDateOfJoining(userDto.getDoj());
		user.setPinCode(userDto.getPinCode());
		user.setIsDelete(false);
	}

	private void mapUserToUserDto(UserDto userDto, User user) {
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setDob(user.getDateOfBirth());
		userDto.setDoj(user.getDateOfJoining());
		userDto.setPinCode(user.getPinCode());
	}

	@Override
	public void deleteUser(Long id) throws UserServiceException {
		User user = Optional.ofNullable(userRepository.findByIdAndIsDelete(id, false))
				.orElseThrow(() -> new UserServiceException(AppConstants.ErrorMsgs.USER_NOT_FOUND));
		user.setIsDelete(true);
		userRepository.save(user);
	}

	@Override
	public void deleteUserFromDb(Long id) throws UserServiceException {
		Optional.ofNullable(userRepository.findById(id))
				.orElseThrow(() -> new UserServiceException(AppConstants.ErrorMsgs.USER_NOT_FOUND));
		userRepository.deleteById(id);
	}

	@Override
	public List<UserDto> getUserList() {
		List<User> userList = userRepository.getUsersOrderByDOBAndDOJ();
		List<UserDto> userDtoList = new ArrayList<>();
		userList.stream().forEach(u -> {
			UserDto userDto = UserDto.builder().build();
			mapUserToUserDto(userDto, u);
			userDtoList.add(userDto);
		});
		return userDtoList;
	}

	@Override
	public List<User> searchUsersByFilter(String firstName, String lastName, String pinCode) {
		return userRepository.searchUserByFilter(firstName, lastName, pinCode);
	}
}
