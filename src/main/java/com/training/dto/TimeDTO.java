package com.training.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeDTO {

	private UUID timeId;
	private int month;
	private int quarter;
	private int year;
	private ZonedDateTime createdAt;
	private ZonedDateTime modifiedAt;

	public TimeDTO() {
	}

	public TimeDTO(UUID timeId, int month, int quarter, int year, ZonedDateTime createdAt, ZonedDateTime modifiedAt) {
		this.timeId = timeId;
		this.month = month;
		this.quarter = quarter;
		this.year = year;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

//	@JsonIgnore
	public UUID getTimeId() {
		return timeId;
	}

	@JsonProperty
	public void setTimeId(UUID timeId) {
		this.timeId = timeId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// @JsonIgnore
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	@JsonProperty
	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	// @JsonIgnore
	public ZonedDateTime getModifiedAt() {
		return modifiedAt;
	}

	@JsonProperty
	public void setModifiedAt(ZonedDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
