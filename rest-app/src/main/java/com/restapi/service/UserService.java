package com.restapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.restapi.controller.UserController;
import com.restapi.exception.UserCreationException;
import com.restapi.exception.UserNotFoundException;
import com.restapi.model.User;
import com.restapi.repository.UserRepository;


/**
 * @author PriyankaRodi
 * @param userRepository
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);

	/*return all user*/
	public List<User> getAll() throws UserNotFoundException,Exception {
		
		return userRepository.findAll();
		
	}

	/*create single user */
	public String addUser(User user) throws UserCreationException, Exception {
		String response;
		if(userRepository.addUser(user)) response="Successfully added";
		else {
			response="Somthing went wrong,entry not added, please try again.";
		throw new UserCreationException(response);}
		return response;
	}

	/* retrive user by id */
	public User getByUserId(int userId) throws UserNotFoundException,Exception {
		try {
		return userRepository.findByUserId(userId);
		}
		catch(DataAccessException e) {
			throw new UserNotFoundException(userId);
		}
		
		
	}

	/*add multiple user*/
	public String addAll(List<User> userList) throws UserCreationException,Exception {
		// TODO Auto-generated method stub
		String response;
		 if(userRepository.addAllUser(userList)) response="Successfully added all record";
		else {
			response="Not able to add all record, Somthing went wrong.";
		 throw new UserCreationException(response);}
		 return response;
	}

	

	
}
