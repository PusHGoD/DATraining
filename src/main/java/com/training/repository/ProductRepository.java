package com.training.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.training.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	List<Product> findByItem(int item);

	@Modifying
	@Query("update Product p set p.item=?1, p.sClass=?2, p.inventory=?3, p.modifiedAt =?4 where p.productId=?5")
	void updateProduct(int item, String sClass, String inventory, LocalDateTime modifiedAt, UUID productId);

}
