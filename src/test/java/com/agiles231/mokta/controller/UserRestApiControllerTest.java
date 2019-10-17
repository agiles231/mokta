package com.agiles231.mokta.controller;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestApiControllerTest {

	@Autowired
	DataSource dataSource;
	@Autowired
	MockMvc mockMvc;
	
	@Value("${baseUrl}")
	String baseUrl;
	
	@Before
	public void setup() {
		cleanup();
		Resource population = new ClassPathResource("userServiceTest.sql");
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(population);
		databasePopulator.execute(dataSource);
	}
	
	@Test
	public void testRestApi() throws Exception {
		String urlTemplate = baseUrl + "/users/{id}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(urlTemplate, new Object[] {"123"});
		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@After
	public void cleanup() {
		Resource deleteAll = new ClassPathResource("deleteAll.sql");
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(deleteAll);
		databasePopulator.execute(dataSource);
	}
}
