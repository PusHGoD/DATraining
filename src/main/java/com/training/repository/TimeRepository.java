package com.training.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.training.model.jpa.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, UUID>, QuerydslPredicateExecutor<Time> {

}
