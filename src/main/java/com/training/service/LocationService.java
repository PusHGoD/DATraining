package com.training.service;

import org.springframework.stereotype.Service;

import com.training.model.Location;

@Service
public interface LocationService {

	public Location addLocation(Location location);

	public int updateLocation(Location location);
}
