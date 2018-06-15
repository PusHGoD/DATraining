package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.cassandra.ProductCassandra;

@Repository
public interface ProductCassRepository extends CassandraRepository<ProductCassandra, UUID> {

}
