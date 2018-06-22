package com.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.TimeCass;
import com.training.model.jpa.Time;
import com.training.repository.TimeCassRepository;
import com.training.repository.TimeRepository;
import com.training.service.BaseService;
import com.training.service.TimeService;
import com.training.utils.DateTimeUtil;

public class TimeServiceImpl extends BaseService implements TimeService {

	@Autowired
	public TimeCassRepository cassRepository;

	@Autowired
	public TimeRepository jpaRepository;

	@Override
	public List<TimeCass> getAllTimes() {
		return cassRepository.findAll();
	}

	@Override
	public Time addTime(Time time) {
		return jpaRepository.save(time);
	}

	@Override
	public Time updateTime(Time time) {
		if (!jpaRepository.findById(time.getTimeId()).isPresent()) {
			throw new NoDataFoundException("Time ID '" + time.getTimeId() + "' not found in DB");
		}
		time.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(time);
	}

}
