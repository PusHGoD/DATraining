package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.Time;

public interface TimeCassRepository extends CassandraRepository<Time, UUID> {

}
