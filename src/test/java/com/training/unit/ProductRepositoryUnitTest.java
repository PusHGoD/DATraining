package com.training.unit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.repository.ProductRepository;

public class ProductRepositoryUnitTest {

	ProductRepository repository;
	
	@Test
	public void test_pass() {
		assertTrue(true);
	}
	
}
