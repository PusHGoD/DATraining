package com.training.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.training.model.jpa.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID>, QuerydslPredicateExecutor<Location> {

}
