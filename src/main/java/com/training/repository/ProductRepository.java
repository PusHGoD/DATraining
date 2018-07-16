package com.training.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.training.model.jpa.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, QuerydslPredicateExecutor<Product> {

//	@Modifying
//	@Query("update Product p set p.item = ?1, p.sClass=?2, p.inventory=?3, p.modifiedAt=?4 where p.productId=?5")
//	int setProductByProductId(int item, String sClass, String inventory, ZonedDateTime modifiedAt, UUID productId);

}
