package com.training.integration;

import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.dto.ProductDTO;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	UUID testUuid = UUID.fromString("b3c38102-7057-11e8-8754-c3e87a3d914c");
	UUID testUuid2 = UUID.fromString("10b7f32a-fd4d-432b-8b53-d776db75b751");
	UUID wrongTestUuid = UUID.fromString("c381032b-7057-11e8-8754-c3e87a3ddddc");

	@Test
	public void testITWorking() {
		ProductDTO obj = restTemplate.getForObject("/product?id=" + testUuid.toString(), ProductDTO.class);
		// Client client = responseEntity.getBody();
		assertNull(obj.getProductId());
	}
}
