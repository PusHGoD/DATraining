package com.training.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.model.Location;
import com.training.model.Product;
import com.training.model.Time;

public class SalesDTO {

	private Product product;
	private Location location;
	private Time time;
	private int dollars;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	public SalesDTO() {
	}

	public SalesDTO(Product product, Location location, Time time, int dollars, LocalDateTime createdAt,
			LocalDateTime modifiedAt) {
		this.product = product;
		this.location = location;
		this.time = time;
		this.dollars = dollars;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public int getDollars() {
		return dollars;
	}

	public void setDollars(int dollars) {
		this.dollars = dollars;
	}

	@JsonIgnore
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@JsonProperty
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@JsonIgnore
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	@JsonProperty
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
