package com.training.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.training.exception.NoDataFoundException;
import com.training.model.cassandra.LocationCass;
import com.training.model.jpa.Location;
import com.training.repository.LocationCassRepository;
import com.training.repository.LocationRepository;
import com.training.service.BaseService;
import com.training.service.LocationService;
import com.training.utils.DateTimeUtil;

@Service
public class LocationServiceImpl extends BaseService implements LocationService {

	@Autowired
	private LocationCassRepository cassRepository;

	@Autowired
	private LocationRepository jpaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<LocationCass> getAllLocations() {
		return cassRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = false)
	public Location addLocation(Location location) {
		location.setCreatedAt(DateTimeUtil.getCurrent());
		location.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(location);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(readOnly = false)
	public Location updateLocation(Location location) {
		if (!jpaRepository.findById(location.getLocationId()).isPresent()) {
			throw new NoDataFoundException("Location ID '" + location.getLocationId() + "' not found in DB");
		}
		location.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(location);
	}

	@Override
	@Transactional(readOnly = true)
	public Location getOneLocationByQueryDslFromJpa(Predicate predicate) {
		Optional<Location> result = jpaRepository.findOne(predicate);
		if (!result.isPresent()) {
			throw new NoDataFoundException("Not found data in DB");
		}
		return result.get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Location> getLocationByQueryDslFromJpa(Predicate predicate) {
		List<Location> list = new ArrayList<>();
		jpaRepository.findAll(predicate).forEach(list::add);
		return list;
	}
}
