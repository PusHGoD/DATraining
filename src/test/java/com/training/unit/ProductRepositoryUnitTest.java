package com.training.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
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
	
	@Test
	public void testGetAllProducts(){
		
	}
}
