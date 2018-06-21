package com.training.model.jpa;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

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
	private DateTime createdAt;
	private DateTime modifiedAt;

	public Product() {
	}

	public Product(UUID productId, int item, String sClass, String inventory, DateTime createdAt,
			DateTime modifiedAt) {
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
	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "modified_at", nullable = false)
	public DateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(DateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
