package com.training.model.cassandra;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;
import com.training.model.Location;
import com.training.model.Product;
import com.training.model.Time;

@Table("sales")
public class SalesCass implements Serializable {

	private Product product;
	private Location location;
	private Time time;
	private int dollars;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	@PrimaryKeyColumn(name = "product_id", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
	@CassandraType(type = Name.UUID)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@PrimaryKeyColumn(name = "location_id", type = PrimaryKeyType.CLUSTERED, ordinal = 2)
	@CassandraType(type = Name.UUID)
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@PrimaryKeyColumn(name = "time_id", type = PrimaryKeyType.CLUSTERED, ordinal = 3)
	@CassandraType(type = Name.UUID)
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Column("dollars")
	public int getDollars() {
		return dollars;
	}

	public void setDollars(int dollars) {
		this.dollars = dollars;
	}

	@Column("created_at")
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column("modified_at")
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
