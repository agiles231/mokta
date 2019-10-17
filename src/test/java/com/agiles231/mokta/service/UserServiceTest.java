package com.agiles231.mokta.service;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;

import com.agiles231.mokta.domain.user.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserService userService;
	
	@Before
	public void setup() {
		cleanup();
		Resource population = new ClassPathResource("userServiceTest.sql");
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(population);
		databasePopulator.execute(dataSource);
	}

	@Test
	public void testGetUser() {
		String id = "123";

		User user = userService.getUserByLoginOrId(id);
		Assert.assertTrue("Id incorrect. Expected: 123, Actual: " + id, id.equals("123"));
		System.out.println(user);

		user = userService.getUserByLoginOrId("agiles@domain.com");
		String login = (String)user.getProfile().get("login");
		Assert.assertTrue("Login incorrect. Expected: agiles@domain.com, Actual: " + login, login.equals("agiles@domain.com"));
		System.out.println(user);
		
		Map<String, Object> profile = new HashMap<>();
		profile.put("login", "agiles@domain2.org");
		userService.partialUpdateUserByLoginOrId(user.getId(), null, profile);
		user = userService.getUserByLoginOrId(id);
		login = (String)user.getProfile().get("login");
		Assert.assertTrue("Login incorrect. Expected: agiles@domain2.org, Actual: " + login, login.equals("agiles@domain2.org"));
		System.out.println(user);
	}
	
	@After
	public void cleanup() {
		Resource deleteAll = new ClassPathResource("deleteAll.sql");
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(deleteAll);
		databasePopulator.execute(dataSource);
	}
}
