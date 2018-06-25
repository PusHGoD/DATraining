package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.cassandra.ProductCass;

public interface ProductCassRepository extends CassandraRepository<ProductCass, UUID> {

	ProductCass findOneByItem(int item);

	ProductCass findOneByProductId(UUID productId);
}
