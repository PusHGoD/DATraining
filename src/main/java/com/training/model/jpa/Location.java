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
@Table(name = "location")
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UUID locationId;
	private String country;
	private String city;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;

	public Location() {
	}

	public Location(UUID locationId, String country, String city, ZonedDateTime createdAt, ZonedDateTime modifiedAt) {
		this.locationId = locationId;
		this.country = country;
		this.city = city;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	@Id
	@GeneratedValue
	@Column(name = "location_id", unique = true, nullable = false)
	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	@Column(name = "country", nullable = false)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "city", nullable = false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "created_at", nullable = false)
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
