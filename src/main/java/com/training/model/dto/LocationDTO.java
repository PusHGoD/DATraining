package com.training.model.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {

	private UUID locationId;
	private String country;
	private String city;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;

	public LocationDTO() {
	}

	public LocationDTO(UUID locationId, String country, String city, ZonedDateTime createdAt, ZonedDateTime modifiedAt) {
		this.locationId = locationId;
		this.country = country;
		this.city = city;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	// @JsonIgnore
	public UUID getLocationId() {
		return locationId;
	}

	@JsonProperty
	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
