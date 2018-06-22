package com.training.model.dto;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesDTO {

	private int productId;
	private int locationId;
	private int timeId;
	private int dollars;
	private DateTime createdAt;
	private DateTime modifiedAt;

	public SalesDTO() {
	}

	public SalesDTO(int productId, int locationId, int timeId, int dollars, DateTime createdAt, DateTime modifiedAt) {
		this.productId = productId;
		this.locationId = locationId;
		this.timeId = timeId;
		this.dollars = dollars;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getTimeId() {
		return timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}

	public int getDollars() {
		return dollars;
	}

	public void setDollars(int dollars) {
		this.dollars = dollars;
	}

	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Bangkok")
	public DateTime getCreatedAt() {
		return createdAt;
	}

	@JsonProperty
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Bangkok")
	public DateTime getModifiedAt() {
		return modifiedAt;
	}

	@JsonProperty
	public void setModifiedAt(DateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
