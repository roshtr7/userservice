package com.userservice.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.userservice.dto.ResponseDto;
import com.userservice.exception.UserServiceException;
import com.userservice.util.AppConstants;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ UserServiceException.class })
	public ResponseEntity<ResponseDto> handleThreadException(UserServiceException ex) {
		List<String> errors = logErrorsAndGetErrorResponseArray(ex, Optional.empty());
		return new ResponseEntity<>(ResponseDto.builder().errors(errors).build(), HttpStatus.UNPROCESSABLE_ENTITY);

	}

	@ExceptionHandler({ RuntimeException.class, Exception.class })
	public ResponseEntity<ResponseDto> handleException(Exception ex) {
		List<String> errors = logErrorsAndGetErrorResponseArray(ex,
				Optional.of(AppConstants.ErrorMsgs.UNEXPECTED_ERROR));
		return new ResponseEntity<>(ResponseDto.builder().errors(errors).build(), HttpStatus.UNPROCESSABLE_ENTITY);

	}

	private List<String> logErrorsAndGetErrorResponseArray(Exception ex, Optional<String> defaultErrorMsg) {
		List<String> errors = new ArrayList<>();
		if (defaultErrorMsg.isPresent()) {
			errors.add(AppConstants.ErrorMsgs.UNEXPECTED_ERROR);
		}
		errors.add(ex.getMessage());
		logger.error("---------***   Exception occured  ***---------");
		logger.error(ex.toString(), ex);
		return errors;
	}

}