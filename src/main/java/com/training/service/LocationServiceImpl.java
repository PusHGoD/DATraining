package com.training.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.exception.NoDataFoundException;
import com.training.model.Location;
import com.training.model.cassandra.LocationCass;
import com.training.repository.LocationCassRepository;
import com.training.repository.LocationRepository;
import com.training.utils.DateTimeUtil;

@Service
public class LocationServiceImpl extends BaseService implements LocationService {

	public static final Logger log = LoggerFactory.getLogger(LocationServiceImpl.class);

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
