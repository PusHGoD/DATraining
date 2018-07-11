package com.training.unit;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hamcrest.beans.HasPropertyWithValue;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.controller.BaseControllerExceptionHandler;
import com.training.controller.ProductController;
import com.training.dto.ProductDTO;
import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.ProductCass;
import com.training.model.jpa.Product;
import com.training.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
// @TestPropertySource(locations = "classpath:application-test.properties")
// @SpringBootTest
public class ProductControllerUnitTest {

	public MockMvc mockMvc;

	@InjectMocks
	private ProductController controller;

	@Mock
	private ProductService service;

	UUID testUuid = UUID.fromString("b3c38102-7057-11e8-8754-c3e87a3d914c");
	UUID testUuid2 = UUID.fromString("10b7f32a-fd4d-432b-8b53-d776db75b751");
	UUID wrongTestUuid = UUID.fromString("c381032b-7057-11e8-8754-c3e87a3ddddc");

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new BaseControllerExceptionHandler())
				.build();
	}

	@Test
	public void testGetAllProducts() throws Exception {
		ProductCass product1 = new ProductCass();
		product1.setProductId(testUuid);
		ProductCass product2 = new ProductCass();
		product2.setProductId(testUuid2);
		List<ProductCass> list = new ArrayList<ProductCass>();
		list.add(product1);
		list.add(product2);
		when(service.getAllProducts()).thenReturn(list);
		mockMvc.perform(get("/product/")).andExpect(status().isOk()).andExpect(jsonPath("$.length()", is(2)))
				.andExpect(jsonPath("$[0].productId", is(testUuid.toString())))
				.andExpect(jsonPath("$[1].productId", is(testUuid2.toString())));
		verify(service, times(1)).getAllProducts();
	}

	@Test
	public void testGetProductById() throws Exception {
		ProductCass product = new ProductCass();
		product.setProductId(testUuid);
		when(service.getProductById(testUuid)).thenReturn(product);
		mockMvc.perform(get("/product?id=" + testUuid.toString())).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId", is(testUuid.toString())));
	}

	@Test
	public void testGetProductByNonexistentId() throws Exception {
		ProductCass product = new ProductCass();
		product.setProductId(wrongTestUuid);
		mockMvc.perform(get("/product?id=" + testUuid.toString())).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Not found product")));
	}

	@Test
	public void testAddProduct() throws Exception {
		Product product = new Product();
		product.setProductId(testUuid);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(product);
		when(service.addProduct(ArgumentMatchers.argThat(new ArgumentMatcher<Product>() {
			@Override
			public boolean matches(Product arg0) {
				return arg0.getProductId().equals(testUuid);
			}
		}))).thenReturn(product);
		mockMvc.perform(post("/product/add").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.productId", is(testUuid.toString())));
	}

	@Test
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		product.setProductId(testUuid);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(product);
		when(service.updateProduct(ArgumentMatchers.argThat(new ArgumentMatcher<Product>() {
			@Override
			public boolean matches(Product arg0) {
				return arg0.getProductId().equals(testUuid);
			}
		}))).thenReturn(product);
		mockMvc.perform(put("/product/update").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andExpect(jsonPath("$.productId", is(testUuid.toString())));
	}
}
