package com.training.service;

import java.util.List;

import com.querydsl.core.types.Predicate;
import com.training.model.cassandra.LocationCass;
import com.training.model.jpa.Location;

public interface LocationService {

	public List<LocationCass> getAllLocations();

	public Location addLocation(Location location);

	public Location updateLocation(Location location);

	public Location getOneLocationByQueryDslFromJpa(Predicate predicate);

	public List<Location> getLocationByQueryDslFromJpa(Predicate predicate);
}
