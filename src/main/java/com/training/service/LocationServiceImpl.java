package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.exception.NoDataFoundException;
import com.training.model.Location;
import com.training.model.cassandra.LocationCass;
import com.training.repository.LocationCassRepository;
import com.training.repository.LocationRepository;
import com.training.utils.DateTimeUtil;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationCassRepository cassRepository;

	@Autowired
	private LocationRepository jpaRepository;

	@Override
	public List<LocationCass> getAllLocations() {
		return cassRepository.findAll();
	}

	@Override
	public Location addLocation(Location location) {
		location.setCreatedAt(DateTimeUtil.getCurrent());
		location.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(location);
	}

	@Override
	public Location updateLocation(Location location) {
		if (!jpaRepository.findById(location.getLocationId()).isPresent()) {
			throw new NoDataFoundException("Location ID '" + location.getLocationId() + "' not found in DB");
		}
		location.setModifiedAt(DateTimeUtil.getCurrent());
		return jpaRepository.save(location);
	}

}
