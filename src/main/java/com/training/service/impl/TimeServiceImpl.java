package com.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.TimeCass;
import com.training.model.jpa.Time;
import com.training.repository.TimeCassRepository;
import com.training.repository.TimeRepository;
import com.training.service.BaseService;
import com.training.service.TimeService;
import com.training.utils.DateTimeUtil;

@Service
public class TimeServiceImpl extends BaseService implements TimeService {

	@Autowired
	public TimeCassRepository cassRepository;

	@Autowired
	public TimeRepository jpaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TimeCass> getAllTimes() {
		return cassRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = true)
	public Time addTime(Time time) {
		time.setCreatedAt(DateTimeUtil.getCurrent());
		time.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(time);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = false)
	public Time updateTime(Time time) {
		if (!jpaRepository.findById(time.getTimeId()).isPresent()) {
			throw new NoDataFoundException("Time ID '" + time.getTimeId() + "' not found in DB");
		}
		time.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(time);
	}

}
