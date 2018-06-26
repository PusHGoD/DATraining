package com.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
