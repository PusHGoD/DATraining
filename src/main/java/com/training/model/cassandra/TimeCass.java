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

@Table("time")
public class TimeCass implements Serializable {

	private UUID timeId;
	private int month;
	private int quarter;
	private int year;
	private DateTime createdAt;
	private DateTime modifiedAt;

	@PrimaryKeyColumn(name = "time_id", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
	@CassandraType(type = Name.UUID)
	public UUID getTimeId() {
		return timeId;
	}

	public void setTimeId(UUID timeId) {
		this.timeId = timeId;
	}

	@Column("month")
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Column("quarter")
	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	@Column("year")
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
