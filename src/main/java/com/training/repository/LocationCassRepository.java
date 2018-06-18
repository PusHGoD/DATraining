package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.Location;

public interface LocationCassRepository extends CassandraRepository<Location, UUID> {

}
