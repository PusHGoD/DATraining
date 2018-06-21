package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.cassandra.LocationCass;
import com.training.model.jpa.Location;

public interface LocationCassRepository extends CassandraRepository<LocationCass, UUID> {

}
