package com.restapi.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
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
	public List<User> findAll() throws Exception{
		// TODO Auto-generated method stub
		return jdbcTemplate.query(SELECT_ALL, rowMapper);
	}
	
	public boolean addUser(User user) throws Exception {
		if(jdbcTemplate.update(INSERT_USER,user.getFirstName(),user.getLastName(),user.getGender(),user.getAge())>0) 
			return true;
		else
			return false;
	}

	public User findByUserId(int userId) throws Exception{
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
