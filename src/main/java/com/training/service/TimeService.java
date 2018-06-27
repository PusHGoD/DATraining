package com.training.service;

import java.util.List;

import com.training.model.cassandra.TimeCass;
import com.training.model.jpa.Time;

public interface TimeService {
	
	public List<TimeCass> getAllTimes();

	public Time addTime(Time time);

	public Time updateTime(Time time);
}
