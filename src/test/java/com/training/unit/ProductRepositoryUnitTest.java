package com.training.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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

	UUID testUuid = UUID.fromString("b3c38100-7057-11e8-8754-c3e87a3d914c");
	UUID testUuid2 = UUID.fromString("10b7f32a-fd4d-432b-8b53-d776db75b751");
	UUID wrongTestUuid = UUID.fromString("c381032b-7057-11e8-8754-c3e87a3ddddc");

	@Test
	@Transactional
	public void testGetProductById() {
		Optional<Product> result = repository.findById(testUuid);
		assertEquals(testUuid, result.get().getProductId());
	}

	@Test
	@Transactional
	public void testGetProductByNonExistentId() {
		Optional<Product> result = repository.findById(wrongTestUuid);
		assertFalse(result.isPresent());
	}

	@Test
	@Transactional
	public void testGetAllProducts() {
		List<Product> result = repository.findAll();
		assertFalse(result.isEmpty());
		assertTrue(result.size() == 1);
	}

	@Test
	@Transactional
	public void testAddProduct() {
		Product result = repository
				.save(new Product(testUuid2, 14, "TEST", "test", ZonedDateTime.now(), ZonedDateTime.now()));
		assertNotNull(result);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	@Transactional
	public void testAddNullProduct() {
		repository.save(null);
	}
}
