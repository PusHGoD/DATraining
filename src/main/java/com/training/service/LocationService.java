package com.training.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.model.Location;
import com.training.model.cassandra.LocationCass;

@Service
public interface LocationService {

	@Transactional(readOnly = true)
	public List<LocationCass> getAllLocations();

	@Transactional(readOnly = false)
	public Location addLocation(Location location);

	@Transactional(readOnly = false)
	public Location updateLocation(Location location);

}
