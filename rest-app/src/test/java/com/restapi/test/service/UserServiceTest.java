package com.restapi.test.service;


import static org.junit.jupiter.api.Assertions.assertEquals;



import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.restapi.model.User;
import com.restapi.repository.UserRepository;
import com.restapi.service.UserService;



/**
 * @author PriyankaRodi
 * @param mockMvc
 * @param userService
 * @param mockUser1
 * @param mockUser2
 * @param userList
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserService.class)
public class UserServiceTest {
	/*
	 * @Autowired private MockMvc mockMvc;
	 */
	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	private User mockUser1 =new User();
	private User mockUser2 =new User();
	private List<User> userList=new ArrayList<>();
	
	public UserServiceTest() {
		this.mockUser1.setUserId(1);
		this.mockUser1.setFirstName("Anushka");
		this.mockUser1.setLastName("Sharma");
		this.mockUser1.setGender("Female");
		this.mockUser1.setAge(26);
		
		this.mockUser2.setUserId(2);
		this.mockUser2.setFirstName("anant");
		this.mockUser2.setLastName("chopda");
		this.mockUser2.setGender("male");
		this.mockUser2.setAge(30);
		
		this.userList.add(mockUser1);
		this.userList.add(mockUser2);
	}
	/*test case for getAll()*/
	@Test
	public void testGetAll() throws Exception{
	
		Mockito.when(userRepository.findAll()).thenReturn(this.userList);
		
		List<User> userList1=userService.getAll();
		assertEquals(this.userList, userList1);
	}
	
	/*test case for getUserById()*/
	@Test
	public void testGetByUserId() throws Exception {
		Mockito.when(userRepository.findByUserId(this.mockUser1.getUserId())).thenReturn(this.mockUser1);
		
		User user=userService.getByUserId(this.mockUser1.getUserId());
		assertEquals(this.mockUser1, user);
	}
	
	/*test case for addUser()*/
	@Test
	public void testAddUser() throws Exception{
		Mockito.when(userRepository.addUser(this.mockUser1)).thenReturn(true);
		String actual=userService.addUser(this.mockUser1);
		assertEquals("Successfully added",actual);
	}
	
	/*test case for addAll()*/
	@Test
	public void testAddAll() throws Exception{
		Mockito.when(userRepository.addAllUser(this.userList)).thenReturn(true);
		String actual=userService.addAll(this.userList);
		assertEquals("Successfully added all record", actual);
	}

}
