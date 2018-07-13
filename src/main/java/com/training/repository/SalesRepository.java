package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.training.model.jpa.Sales;
import com.training.model.jpa.SalesId;

@Repository
public interface SalesRepository extends JpaRepository<Sales, SalesId>, QuerydslPredicateExecutor<Sales> {

}
