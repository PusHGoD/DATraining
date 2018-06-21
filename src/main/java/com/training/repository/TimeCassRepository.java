package com.training.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.training.model.cassandra.TimeCass;
import com.training.model.jpa.Time;

public interface TimeCassRepository extends CassandraRepository<TimeCass, UUID> {

}
