package com.restapi.test.controller;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.controller.UserController;
import com.restapi.model.User;
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
@WebMvcTest(value = UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	private User mockUser1 =new User();
	private User mockUser2 =new User();
	private List<User> userList=new ArrayList<>();
	
	public UserControllerTest() {
		// TODO Auto-generated constructor stub
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
	
	@Test
	public void testGetAll() throws Exception{
		
		Mockito.when(userService.getAll()).thenReturn(this.userList);
		
		String URI="/users/getAllUser";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String expectedJson=this.mapToJson(this.userList);
		String outputInJson= result.getResponse().getContentAsString();
		assertEquals(expectedJson, outputInJson);
		
	}
	@Test
	public void testAddUser() throws Exception
	{

		String inputInJson =this.mapToJson(mockUser1);
		
		String URI="/users/createUser";
		
		Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn("Successfully added");
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
				MockHttpServletResponse response=result.getResponse();
				
				String outputInJson =response.getContentAsString();
				assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	@Test
	public void testGetUserById() throws Exception{
		
		Mockito.when(userService.getByUserId(Mockito.anyInt())).thenReturn(this.mockUser2);
		String URI="/users/getByid/1";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		String expectedJson=this.mapToJson(this.mockUser2);
		String outputInJson= result.getResponse().getContentAsString();
		assertEquals(expectedJson, outputInJson);
	}
	
	@Test
	public void testAddAll() throws Exception{
		String inputInJson =this.mapToJson(this.userList);
		String URI="/users/createAll";
		
		Mockito.when(userService.addAll(Mockito.anyList())).thenReturn("Successfully added");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
				MockHttpServletResponse response=result.getResponse();
				
				String outputInJson =response.getContentAsString();
				
				assertEquals(HttpStatus.OK.value(), response.getStatus());
		
	}
	/**
	 * 
	Map an object into a json string */
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
