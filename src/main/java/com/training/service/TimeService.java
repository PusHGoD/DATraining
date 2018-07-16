package com.training.service;

import java.util.List;

import com.querydsl.core.types.Predicate;
import com.training.model.cassandra.TimeCass;
import com.training.model.jpa.Time;

public interface TimeService {
	
	public List<TimeCass> getAllTimes();

	public Time addTime(Time time);

	public Time updateTime(Time time);
	
	public List<Time> getTimeByQueryDslFromJpa(Predicate predicate);
}
