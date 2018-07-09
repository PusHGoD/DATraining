package com.training.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.model.jpa.Product;
import com.training.repository.ProductRepository;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class ProductRepositoryUnitTest {

	@Autowired
	private ProductRepository repository;

	UUID testUuid = UUID.fromString("b3c38102-7057-11e8-8754-c3e87a3d914c");

	@Before
	@Transactional
	public void initializeData() {
		Product product = new Product();
		product.setProductId(testUuid);
		repository.save(product);
	}

	@Test
	@Transactional
	public void testGetProductById() {
		Optional<Product> result = repository.findById(testUuid);
		assertEquals(testUuid, result.get().getProductId());
	}

	@Test
	public void testGetAllProducts() {
		List<Product> result = repository.findAll();
		assertFalse(result.isEmpty());
	}
}
