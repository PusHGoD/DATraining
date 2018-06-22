package com.training.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.training.model.cassandra.TimeCass;
import com.training.model.jpa.Time;

public interface TimeService {
	@Transactional(readOnly = true)
	public List<TimeCass> getAllTimes();

	@Transactional(readOnly = false)
	public Time addTime(Time time);

	@Transactional(readOnly = false)
	public Time updateTime(Time time);
}
