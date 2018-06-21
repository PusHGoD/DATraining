package com.training.model.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SalesId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productId;
	private int timeId;
	private int locationId;

	public SalesId() {
	}

	public SalesId(int productId, int timeId, int locationId) {
		this.productId = productId;
		this.timeId = timeId;
		this.locationId = locationId;
	}

	@Column(name = "product_id", nullable = false)
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name = "time_id", nullable = false)
	public int getTimeId() {
		return timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}

	@Column(name = "location_id", nullable = false)
	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SalesId))
			return false;
		SalesId castOther = (SalesId) other;

		return (this.getProductId() == castOther.getProductId()) && (this.getTimeId() == castOther.getTimeId())
				&& (this.getLocationId() == castOther.getLocationId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProductId();
		result = 37 * result + this.getTimeId();
		result = 37 * result + this.getLocationId();
		return result;
	}
}
