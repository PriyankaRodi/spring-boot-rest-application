package com.restapi.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;

import com.restapi.model.User;
import com.restapi.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@MockBean
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserRepository userRepository;

	private User mockUser1 = new User();
	private User mockUser2 = new User();
	private List<User> userList = new ArrayList<>();

	public UserRepositoryTest() {
		this.mockUser1.setUserId(1);
		this.mockUser1.setFirstName("priyanka");
		this.mockUser1.setLastName("rodi");
		this.mockUser1.setGender("female");
		this.mockUser1.setAge(27);

		this.mockUser2.setUserId(2);
		this.mockUser2.setFirstName("anant");
		this.mockUser2.setLastName("chopda");
		this.mockUser2.setGender("male");
		this.mockUser2.setAge(30);

		this.userList.add(mockUser1);
		this.userList.add(mockUser2);
	}

	@Test
	public void testFindByUserId() throws Exception {
		injectImMemoryDataSource();
		Mockito.when(jdbcTemplate.queryForObject(Mockito.anyString(), Mockito.eq(Integer.class))).thenReturn(1);
		User user = userRepository.findByUserId(1);
		assertEquals(mockUser1.getUserId(), user.getUserId());
		assertEquals(mockUser1.getFirstName(), user.getFirstName());
		assertEquals(mockUser1.getLastName(), user.getLastName());
		assertEquals(mockUser1.getGender(), user.getGender());
		assertEquals(mockUser1.getAge(), user.getAge());
	}

	@Test
	public void testAddUser() throws Exception {
		Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyInt())).thenReturn(1);
		User user = userRepository.findByUserId(mockUser1.getUserId());
		assertEquals(mockUser1.getUserId(), user.getUserId());
		assertEquals(mockUser1.getFirstName(), user.getFirstName());
		assertEquals(mockUser1.getLastName(), user.getLastName());
		assertEquals(mockUser1.getGender(), user.getGender());
		assertEquals(mockUser1.getAge(), user.getAge());

	}

	/* test case for addAllUser() */
	@Test
	public void testAddAllUser() throws Exception {
		injectImMemoryDataSource();
		for (User user : userList) {
			Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
					Mockito.anyString(), Mockito.anyInt())).thenReturn(1);
		}
		List<User> actualList = userRepository.findAll();
		assertEquals(userList.size(), actualList.size());
		assertEquals(userList.get(0).getUserId(), actualList.get(0).getUserId());
		assertEquals(userList.get(0).getFirstName(), actualList.get(0).getFirstName());
		assertEquals(userList.get(0).getLastName(), actualList.get(0).getLastName());
		assertEquals(userList.get(0).getGender(), actualList.get(0).getGender());
		assertEquals(userList.get(0).getAge(), actualList.get(0).getAge());

		assertEquals(userList.get(1).getUserId(), actualList.get(1).getUserId());
		assertEquals(userList.get(1).getFirstName(), actualList.get(1).getFirstName());
		assertEquals(userList.get(1).getLastName(), actualList.get(1).getLastName());
		assertEquals(userList.get(1).getGender(), actualList.get(1).getGender());
		assertEquals(userList.get(1).getAge(), actualList.get(1).getAge());

	}
	/* test case for findAll() */

	@Test
	public void testFindAll() throws Exception {
		Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(RowMapper.class))).thenReturn(userList);
		List<User> actualList = userRepository.findAll();
		assertEquals(userList.size(), actualList.size());

		assertEquals(userList.get(0).getUserId(), actualList.get(0).getUserId());
		assertEquals(userList.get(0).getFirstName(), actualList.get(0).getFirstName());
		assertEquals(userList.get(0).getLastName(), actualList.get(0).getLastName());
		assertEquals(userList.get(0).getGender(), actualList.get(0).getGender());
		assertEquals(userList.get(0).getAge(), actualList.get(0).getAge());

		assertEquals(userList.get(1).getUserId(), actualList.get(1).getUserId());
		assertEquals(userList.get(1).getFirstName(), actualList.get(1).getFirstName());
		assertEquals(userList.get(1).getLastName(), actualList.get(1).getLastName());
		assertEquals(userList.get(1).getGender(), actualList.get(1).getGender());
		assertEquals(userList.get(1).getAge(), actualList.get(1).getAge());

	}

	
	  private void injectImMemoryDataSource() { DataSource dataSource=new
	  EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript(
	  "test-schema.sql") .addScript("test-data.sql")
	  .build(); userRepository.setDataSource(dataSource); 
	  }
	 

}
