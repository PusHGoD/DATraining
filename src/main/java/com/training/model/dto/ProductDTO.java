package com.training.model.dto;

import java.util.UUID;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

	private UUID productId;
	private int item;
	private String sClass;
	private String inventory;
	private DateTime createdAt;
	private DateTime modifiedAt;

	public ProductDTO() {

	}

	public ProductDTO(UUID productId, int item, String sClass, String inventory, DateTime createdAt,
			DateTime modifiedAt) {
		this.productId = productId;
		this.item = item;
		this.sClass = sClass;
		this.inventory = inventory;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	// @JsonIgnore
	public UUID getProductId() {
		return productId;
	}

	@JsonProperty
	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	@JsonProperty("class")
	public String getsClass() {
		return sClass;
	}

	public void setsClass(String sClass) {
		this.sClass = sClass;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	// @JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public DateTime getCreatedAt() {
		return createdAt;
	}

	@JsonProperty
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	// @JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public DateTime getModifiedAt() {
		return modifiedAt;
	}

	@JsonProperty
	public void setModifiedAt(DateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
