package com.restapi.repository;

import java.sql.ResultSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.restapi.controller.UserController;
import com.restapi.exception.UserNotFoundException;
import com.restapi.model.User;


/**
 * @author PriyankaRodi
 * @param jdbcTemplate
 *
 */
@Repository
public class UserRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);

	
	private final String SELECT_ALL= "SELECT * FROM user_details";
	
	private final String INSERT_USER="INSERT INTO user_details (firstName,lastName,gender,age) values (?, ?, ?,?)";
	private final String SELECT_BY_USERID="SELECT * from user_details where userId=?";
	
	private RowMapper<User> rowMapper =(ResultSet rs, int rowNum)->{
		User user=new User();
		user.setUserId(rs.getInt(1));
		user.setFirstName(rs.getString(2));
		user.setLastName(rs.getString(3));
		user.setGender(rs.getString(4));
		user.setAge(rs.getInt(5));
		return user;
	};
	public List<User> findAll() throws UserNotFoundException,Exception{
		// TODO Auto-generated method stub
		List<User> userList=jdbcTemplate.query(SELECT_ALL, rowMapper);
		if(userList.size()>0) {
			return userList;
		}else {
			throw new UserNotFoundException("records not available in database");
		}
	}
	
	public boolean addUser(User user) throws Exception {
		if(jdbcTemplate.update(INSERT_USER,user.getFirstName(),user.getLastName(),user.getGender(),user.getAge())>0) 
			return true;
		else
			return false;
	}

	public User findByUserId(int userId) throws DataAccessException,Exception{
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SELECT_BY_USERID,new Object[] {userId},rowMapper);
		 
	}
	
	public boolean addAllUser(List<User> userList) throws Exception {
		boolean flag=false;
		for(User user:userList) {
			if(jdbcTemplate.update(INSERT_USER,user.getFirstName(),user.getLastName(),user.getGender(),user.getAge())>0) 
				flag=true;
			else
				flag= false;
			
		}
		return flag;
	}

}
