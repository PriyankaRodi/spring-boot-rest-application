package com.restapi.exception;

public class UserCreationException extends RuntimeException {

	public UserCreationException() {
		super();
	}

	public UserCreationException(String message) {
		super(message);
	}

}
