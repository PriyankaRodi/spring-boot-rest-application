package com.restapi.exception;



public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(int userId) {
		super("used id not found: "+userId);
	}
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
