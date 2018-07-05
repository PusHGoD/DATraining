package com.training.model.cassandra;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;

@Table("location")
public class LocationCass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5842680488047414610L;
	
	private UUID locationId;
	private String country;
	private String city;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;

	public LocationCass() {
	}

	public LocationCass(UUID locationId, String country, String city, ZonedDateTime createdAt,
			ZonedDateTime modifiedAt) {
		this.locationId = locationId;
		this.country = country;
		this.city = city;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	@PrimaryKeyColumn(name = "location_id", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
	@CassandraType(type = Name.UUID)
	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	@Column("country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column("city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column("created_at")
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column("modified_at")
	public ZonedDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(ZonedDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
