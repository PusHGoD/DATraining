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
@Table(name = "time")
public class Time implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UUID timeId;
	private int month;
	private int quarter;
	private int year;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;

	public Time() {
	}

	public Time(UUID timeId, int month, int quarter, int year, ZonedDateTime createdAt, ZonedDateTime modifiedAt) {
		this.timeId = timeId;
		this.month = month;
		this.quarter = quarter;
		this.year = year;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	@Id
	@GeneratedValue
	@Column(name = "time_id", unique = true, nullable = false)
	public UUID getTimeId() {
		return timeId;
	}

	public void setTimeId(UUID timeId) {
		this.timeId = timeId;
	}

	@Column(name = "month", nullable = false)
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Column(name = "quarter", nullable = false)
	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	@Column(name = "year", nullable = false)
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
