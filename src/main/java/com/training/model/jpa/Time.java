package com.training.model.jpa;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private DateTime createdAt;
	private DateTime modifiedAt;

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
	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "modified_at", nullable = false)
	public DateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(DateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

}
