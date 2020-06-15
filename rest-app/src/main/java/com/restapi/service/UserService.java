package com.restapi.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	public List<User> getAll() throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public String addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		String response;
		if(userRepository.addUser(user)) response="Successfully added";
		else
			response="Somthing went wrong,entry not added, please try again.";
		return response;
	}

	public User getByUserId(int userId) throws Exception {
		return userRepository.findByUserId(userId);
		
		
	}

	public String addAll(List<User> userList) throws Exception {
		// TODO Auto-generated method stub
		String response;
		 if(userRepository.addAllUser(userList)) response="Successfully added all record";
		else
			response="Not able to add all record, Somthing went wrong.";
		 return response;
	}

	

	
}
