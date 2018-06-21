package com.training.model.cassandra;

import java.io.Serializable;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;

@Table("location")
public class LocationCass implements Serializable {
	private UUID locationId;
	private String country;
	private String city;
	private DateTime createdAt;
	private DateTime modifiedAt;

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
	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column("modified_at")
	public DateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(DateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
