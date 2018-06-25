package com.training.model.jpa;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SalesId id;
	private Product product;
	private Location location;
	private Time time;
	private int dollars;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;

	public Sales(SalesId id, Product product, Location location, Time time, int dollars, ZonedDateTime createdAt,
			ZonedDateTime modifiedAt) {
		this.id = id;
		this.product = product;
		this.location = location;
		this.time = time;
		this.dollars = dollars;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Sales() {
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "productId", column = @Column(name = "product_id", nullable = false)),
			@AttributeOverride(name = "timeId", column = @Column(name = "time_id", nullable = false)),
			@AttributeOverride(name = "locationId", column = @Column(name = "location_id", nullable = false)) })
	public SalesId getId() {
		return id;
	}

	public void setId(SalesId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id", insertable = false, updatable = false)
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_id", insertable = false, updatable = false)
	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Column(name = "dollars", nullable = false)
	public int getDollars() {
		return dollars;
	}

	public void setDollars(int dollars) {
		this.dollars = dollars;
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
