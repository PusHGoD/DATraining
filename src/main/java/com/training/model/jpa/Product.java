package com.training.model.jpa;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UUID productId;
	private int item;
	private String sClass;
	private String inventory;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;

	public Product() {
	}

	public Product(UUID productId, int item, String sClass, String inventory, ZonedDateTime createdAt,
			ZonedDateTime modifiedAt) {
		this.productId = productId;
		this.item = item;
		this.sClass = sClass;
		this.inventory = inventory;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	@Id
	@GeneratedValue
	@Column(name = "product_id", unique = true, nullable = false)
	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	@Column(name = "item", nullable = false)
	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	@Column(name = "[class]", nullable = false)
	public String getsClass() {
		return sClass;
	}

	public void setsClass(String sClass) {
		this.sClass = sClass;
	}

	@Column(name = "inventory", nullable = false)
	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	@Column(name = "created_at", nullable = false, updatable = false)
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "modified_at", nullable = false)
	public ZonedDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(ZonedDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
