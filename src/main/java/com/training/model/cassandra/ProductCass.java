package com.training.model.cassandra;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType.Name;
import com.training.model.dto.ProductDTO;

@Table("product")
public class ProductCass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UUID productId;
	private int item;
	private String sClass;
	private String inventory;
	private DateTime createdAt;
	private DateTime modifiedAt;

	public ProductCass() {
	}

	public ProductCass(UUID productId, int item, String sClass, String inventory, DateTime createdAt,
			DateTime modifiedAt) {
		this.productId = productId;
		this.item = item;
		this.sClass = sClass;
		this.inventory = inventory;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	@PrimaryKeyColumn(name = "product_id", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
	@CassandraType(type = Name.UUID)
	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	@Column("item")
	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	@Column("class")
	public String getsClass() {
		return sClass;
	}

	public void setsClass(String sClass) {
		this.sClass = sClass;
	}

	@Column("inventory")
	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	@Column("created_at")
	@Temporal(TemporalType.TIMESTAMP)
	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column("modified_at")
	@Temporal(TemporalType.TIMESTAMP)
	public DateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(DateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
