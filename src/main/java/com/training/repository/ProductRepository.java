package com.training.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.jpa.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	
}
