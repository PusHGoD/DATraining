package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.cassandra.Product;

@Repository
public interface ProductCassandraRepository extends CassandraRepository<Product, UUID> {

}
