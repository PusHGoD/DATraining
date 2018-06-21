package com.training.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.jpa.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, UUID> {

}
