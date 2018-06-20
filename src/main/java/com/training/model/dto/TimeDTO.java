package com.training.model.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.training.model.Sales;

public class TimeDTO {

	private UUID timeId;
	private int month;
	private int quarter;
	private int year;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	public TimeDTO() {
	}

	public TimeDTO(UUID timeId, int month, int quarter, int year, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		this.timeId = timeId;
		this.month = month;
		this.quarter = quarter;
		this.year = year;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	@JsonIgnore
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

	@JsonIgnore
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@JsonProperty
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@JsonIgnore
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	@JsonProperty
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
