package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.cassandra.LocationCass;

public interface LocationCassRepository extends CassandraRepository<LocationCass, UUID> {

}
