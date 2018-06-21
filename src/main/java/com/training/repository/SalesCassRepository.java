package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.cassandra.SalesCass;
import com.training.model.jpa.Sales;

public interface SalesCassRepository extends CassandraRepository<SalesCass, UUID> {

}
