package com.training.model.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

	private UUID productId;
	private int item;
	private String sClass;
	private String inventory;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;

	public ProductDTO() {

	}

	public ProductDTO(UUID productId, int item, String sClass, String inventory, ZonedDateTime createdAt,
			ZonedDateTime modifiedAt) {
		this.productId = productId;
		this.item = item;
		this.sClass = sClass;
		this.inventory = inventory;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	// @JsonIgnore
	@NotNull
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
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	@JsonProperty
	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// @JsonIgnore
	public ZonedDateTime getModifiedAt() {
		return modifiedAt;
	}

	@JsonProperty
	public void setModifiedAt(ZonedDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
