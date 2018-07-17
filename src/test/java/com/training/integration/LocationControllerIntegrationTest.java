package com.training.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.model.jpa.Location;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test.sql")
public class LocationControllerIntegrationTest {

	UUID testUuid = UUID.fromString("e75db8d7-d4ba-4326-8b8d-e9ad1c6f7a61");
	UUID testUuid2 = UUID.fromString("653e635e-b187-49eb-8a0e-a95eeb27b064");
	UUID wrongTestUuid = UUID.fromString("52f3dc34-0ff5-43ee-9617-6037eecac897");

	private static MockMvc mockMvc;

	@Resource
	private WebApplicationContext wac;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity())
				.alwaysDo(MockMvcResultHandlers.print()).build();
	}

	@Test
	@Transactional
	public void testGetAllLocations() throws Exception {
		mockMvc.perform(get("/location").with(httpBasic("admin", "password"))).andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void testGetLocationByQueryDsl_id() throws Exception {
		mockMvc.perform(get("/location/jpa").param("id", testUuid2.toString()).with(httpBasic("user", "password")))
				.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void testGetLocationByQueryDsl_country() throws Exception {
		mockMvc.perform(get("/location/jpa").param("country", "VN").with(httpBasic("user", "password")))
				.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void testGetLocationByQueryDsl_city() throws Exception {
		mockMvc.perform(get("/location/jpa").param("city", "Da Nang").with(httpBasic("user", "password")))
				.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@WithMockUser(username = "dbadmin1", password = "password", roles = { "USER", "ADMIN" })
	public void testAddLocation() throws Exception {
		Location location = new Location();
		location.setLocationId(testUuid);
		location.setCountry("VN");
		location.setCity("Da Nang");
		mockMvc.perform(post("/location/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(location)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.country", is("VN")));
	}

	@Test
	@Transactional
	@WithMockUser(username = "dbadmin1", password = "password", roles = { "USER", "ADMIN" })
	public void testUpdateLocation() throws Exception {
		Location location = new Location();
		location.setLocationId(testUuid2);
		location.setCountry("VN");
		location.setCity("Da Nang");
		mockMvc.perform(put("/location/update").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(location)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.locationId", is(testUuid2.toString())));
	}
}
