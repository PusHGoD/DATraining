package com.training.model.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO implements Serializable{

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Bangkok")
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	@JsonProperty
	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// @JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Bangkok")
	public ZonedDateTime getModifiedAt() {
		return modifiedAt;
	}

	@JsonProperty
	public void setModifiedAt(ZonedDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
