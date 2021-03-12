package com.userservice.exception;

public class UserServiceException extends Exception {

	private static final long serialVersionUID = -5070712613798120345L;

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(String message, Exception ex) {
		super(message, ex);
	}

}
