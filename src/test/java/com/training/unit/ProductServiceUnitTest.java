package com.training.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.ProductCass;
import com.training.model.jpa.Product;
import com.training.repository.ProductCassRepository;
import com.training.repository.ProductRepository;
import com.training.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceUnitTest {

	@InjectMocks
	private ProductServiceImpl service;

	@Mock
	private ProductCassRepository repository;

	@Mock
	private ProductRepository jpaRepository;

	UUID testUuid = UUID.fromString("b3c38100-7057-11e8-8754-c3e87a3d914c");
	UUID testUuid2 = UUID.fromString("10b7f32a-fd4d-432b-8b53-d776db75b751");
	UUID wrongTestUuid = UUID.fromString("c381032b-7057-11e8-8754-c3e87a3ddddc");

	@Test
	public void testGetAllProducts() {
		ProductCass product1 = new ProductCass();
		product1.setProductId(testUuid);
		ProductCass product2 = new ProductCass();
		product2.setProductId(testUuid2);
		List<ProductCass> list = new ArrayList<ProductCass>();
		list.add(product1);
		list.add(product2);
		when(repository.findAll()).thenReturn(list);
		assertEquals(list, service.getAllProducts());
	}

	@Test
	public void testGetProductById() {
		ProductCass product = new ProductCass();
		product.setProductId(testUuid);
		when(repository.findOneByProductId(testUuid)).thenReturn(product);
		assertEquals(product, service.getProductById(testUuid));
	}

	@Test(expected = NoDataFoundException.class)
	public void testGetProductByNonexistentId() {
		ProductCass product = new ProductCass();
		product.setProductId(testUuid);
		assertNotEquals(product, service.getProductById(wrongTestUuid));
	}

	@Test(expected = NullPointerException.class)
	public void testGetProductByNullId() {
		UUID testUuid = null;
		ProductCass product = new ProductCass();
		product.setProductId(testUuid);
		when(repository.findOneByProductId(testUuid)).thenThrow(NullPointerException.class);
		service.getProductById(testUuid);
	}

	@Test
	public void testAddProductCorrectly() {
		Product product = new Product(testUuid2, 15, "Test1", "InventoryTest1", null, null);
		when(jpaRepository.save(product)).thenReturn(product);
		assertEquals(product, service.addProduct(product));
	}

	@Test
	public void testAddProductIncorrectly() {
		Product product = new Product();
		product.setProductId(testUuid2);
		when(jpaRepository.save(product)).thenReturn(null);
		assertNull(service.addProduct(product));
		verify(jpaRepository, times(1)).save(product);
	}

	@Test(expected = NullPointerException.class)
	public void testAddNullProduct() {
		Product product = null;
		service.addProduct(product);
	}

	@Test
	public void testUpdateProduct() {
		Product product = new Product(testUuid, 16, "Test2", "InventoryTest2", null, null);
		when(jpaRepository.findById(testUuid)).thenReturn(Optional.of(product));
		when(jpaRepository.save(product)).thenReturn(product);
		assertEquals(product, service.updateProduct(product));
	}
}
