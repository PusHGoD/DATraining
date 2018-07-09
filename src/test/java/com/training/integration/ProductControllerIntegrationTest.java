package com.training.integration;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.training.controller.ProductController;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

}
