package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.Sales;
import com.training.model.cassandra.SalesCass;

public interface SalesCassRepository extends CassandraRepository<SalesCass, UUID> {

}
