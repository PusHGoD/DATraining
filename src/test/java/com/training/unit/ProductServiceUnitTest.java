package com.training.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.ProductCass;
import com.training.repository.ProductCassRepository;
import com.training.service.impl.ProductServiceImpl;

public class ProductServiceUnitTest {

	@InjectMocks
	private ProductServiceImpl service;

	@Mock
	private ProductCassRepository repository;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
	}

	UUID testUuid = UUID.fromString("b3c38102-7057-11e8-8754-c3e87a3d914c");
	UUID wrongTestUuid = UUID.fromString("c381032b-7057-11e8-8754-c3e87a3ddddc");

	@Test
	public void testGetAllProducts_thenOK() {
		ProductCass product1 = new ProductCass();
		product1.setProductId(testUuid);
		ProductCass product2 = new ProductCass();
		product2.setProductId(wrongTestUuid);
		List<ProductCass> list = new ArrayList<ProductCass>();
		when(repository.findAll()).thenReturn(list);
		assertEquals(service.getAllProducts(), list);
	}

	@Test
	public void testGetProductById_thenOK() {
		ProductCass product = new ProductCass();
		product.setProductId(testUuid);
		when(repository.findOneByProductId(testUuid)).thenReturn(product);
		assertEquals(service.getProductById(testUuid), product);
	}

	@Test(expected = NoDataFoundException.class)
	public void testGetProductByNonexistentId_thenFail() {
		ProductCass product = new ProductCass();
		product.setProductId(testUuid);
		when(repository.findOneByProductId(testUuid)).thenReturn(product);
		service.getProductById(wrongTestUuid);
	}

	@Test(expected = NullPointerException.class)
	public void testGetProductByNullId_thenThrow() {
		UUID testUuid = null;
		ProductCass product = new ProductCass();
		product.setProductId(testUuid);
		when(repository.findOneByProductId(testUuid)).thenThrow(NullPointerException.class);
		service.getProductById(testUuid);
	}
}
