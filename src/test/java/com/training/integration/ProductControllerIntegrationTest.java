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

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
import com.training.model.jpa.Product;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test.sql")
public class ProductControllerIntegrationTest {

	private MockMvc mockMvc;

	@Resource
	private WebApplicationContext wac;

	UUID testUuid = UUID.fromString("b3c38100-7057-11e8-8754-c3e87a3d914c");
	UUID testUuid2 = UUID.fromString("10b7f32a-fd4d-432b-8b53-d776db75b751");
	UUID wrongTestUuid = UUID.fromString("c381032b-7057-11e8-8754-c3e87a3ddddc");

	private final ObjectMapper mapper = new ObjectMapper();

	@BeforeClass
	public static void setupData() {

	}

	@AfterClass
	public static void removeData() {

	}

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity())
				.alwaysDo(MockMvcResultHandlers.print()).build();
	}

	@Test
	@Transactional
	public void testGetAllProducts() throws Exception {
		mockMvc.perform(get("/product").with(httpBasic("user", "password"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[0].productId", is(testUuid.toString())));
	}

	@Test
	@Transactional
	public void testGetProductById() throws Exception {
		mockMvc.perform(get("/product").param("id", testUuid.toString()).with(httpBasic("user", "password")))
				.andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void testGetProductById_withFailAuth() throws Exception {
		mockMvc.perform(
				get("/product").param("id", testUuid.toString()).with(httpBasic("wrong-username", "wrong-password")))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Transactional
	public void testGetProductById_accessDenied() throws Exception {
		mockMvc.perform(get("/product").param("id", testUuid.toString()).with(httpBasic("admin", "password")))
				.andExpect(status().isForbidden());
	}

	@Test
	@Transactional
	public void testGetProductByNonExistentId() throws Exception {
		mockMvc.perform(get("/product").param("id", wrongTestUuid.toString()).with(httpBasic("user", "password")))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Product ID '" + wrongTestUuid.toString() + "' not found in DB")));
	}

	@Test
	@Transactional
	@WithMockUser(username = "dbuser1", password = "password", roles = { "USER", "ADMIN" })
	public void testAddProduct() throws Exception {
		Product product = new Product();
		product.setItem(22);
		product.setsClass("test");
		product.setInventory("test");
		mockMvc.perform(post("/product/add").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(product)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.item", is(22)));
	}

	@Test
	@Transactional
	@WithMockUser(username = "dbuser1", password = "password", roles = { "USER", "ADMIN" })
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		product.setProductId(testUuid);
		product.setItem(22);
		product.setsClass("test");
		product.setInventory("test");
		mockMvc.perform(put("/product/update").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(product)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.productId", is(testUuid.toString())));
	}
}
