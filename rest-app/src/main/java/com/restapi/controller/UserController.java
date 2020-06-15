package com.restapi.controller;

import java.util.List; 


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.exception.UserCreationException;
import com.restapi.exception.UserNotFoundException;
import com.restapi.model.User;
import com.restapi.service.UserService;



/**
 * @author PriyankaRodi
 * @param userService
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	/*return all user*/
	@GetMapping("/getAllUser")
	public List<User> getAll() throws UserNotFoundException,Exception{
		return userService.getAll();
	}
	
	/*create single user */
	@PostMapping("/createUser")
	public String addUser(@RequestBody User user) throws UserCreationException, Exception {
		return userService.addUser(user);
	}
	
	/* retrive user by id */
	@GetMapping("getByid/{userId}") 
	public User getByUserId(@PathVariable int userId) throws UserNotFoundException,Exception {
		return userService.getByUserId(userId);
	}
	
	/*add multiple user*/
	@PostMapping("/createAll")
	public String addAll(@RequestBody List<User> userList) throws UserCreationException, Exception {
		return userService.addAll(userList);
	}

}
