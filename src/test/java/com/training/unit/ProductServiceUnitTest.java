package com.training.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.model.cassandra.ProductCass;
import com.training.repository.ProductCassRepository;
import com.training.service.impl.ProductServiceImpl;

public class ProductServiceUnitTest {

	@InjectMocks
	ProductServiceImpl service;

	@Mock
	ProductCassRepository repository;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetProduct() {
		UUID testUuid = UUID.fromString("b3c38102-7057-11e8-8754-c3e87a3d914c");
		ProductCass product = new ProductCass();
		product.setProductId(testUuid);
		when(repository.findOneByProductId(testUuid)).thenReturn(product);
		assertEquals(service.getProductById(testUuid), product);
	}
}
