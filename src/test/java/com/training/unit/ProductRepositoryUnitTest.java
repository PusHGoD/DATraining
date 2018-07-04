package com.training.unit;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.model.jpa.Product;
import com.training.repository.ProductRepository;

public class ProductRepositoryUnitTest {

	@Autowired
	private ProductRepository repository;

	@Test
	public void testGetProductById() {
//		UUID testUuid = UUID.fromString("b3c38102-7057-11e8-8754-c3e87a3d914c");
//		Product product = new Product();
//		product.setProductId(testUuid);
//		repository.save(product);
//		Optional<Product> result = repository.findById(testUuid);
//		assertEquals(testUuid, result.get().getProductId());
	}
}
