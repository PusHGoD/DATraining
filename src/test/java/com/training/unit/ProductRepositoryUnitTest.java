package com.training.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.training.model.jpa.Product;
import com.training.repository.ProductRepository;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test.sql")
// @SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts
// = "classpath:test.sql"),
// @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts =
// "classpath:test-after.sql") })
public class ProductRepositoryUnitTest {

	@Autowired
	private ProductRepository repository;

	UUID testUuid = UUID.fromString("b3c38102-7057-11e8-8754-c3e87a3d914c");

	// @BeforeClass
	// public static void initializeData() {
	// UUID testUuid = UUID.fromString("b3c38102-7057-11e8-8754-c3e87a3d914c");
	//
	// }

	@Test
	@Transactional
	public void testGetProductById() {
		Optional<Product> result = repository.findById(testUuid);
		assertEquals(testUuid, result.get().getProductId());
	}

	@Test
	@Transactional
	public void testGetAllProducts() {
		List<Product> result = repository.findAll();
		assertFalse(result.isEmpty());
	}
}
