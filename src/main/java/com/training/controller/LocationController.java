package com.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.exception.BadRequestException;
import com.training.exception.NoDataFoundException;
import com.training.model.DBType;
import com.training.model.cassandra.LocationCass;
import com.training.model.dto.LocationDTO;
import com.training.model.jpa.Location;
import com.training.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	public static final Logger log = LoggerFactory.getLogger(LocationController.class);

	@Autowired
	private LocationService service;

	@GetMapping(value = "/", headers = "Accept=application/json")
	public List<LocationDTO> getAllLocations() {
		List<LocationCass> list = service.getAllLocations();
		List<LocationDTO> dtoList = list.stream().map(location -> convertToDTO(location, DBType.CASSANDRA))
				.collect(Collectors.toList());
		return dtoList;
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public LocationDTO addLocation(@RequestBody LocationDTO location) {
		return convertToDTO(service.addLocation(convertToJPAEntity(location)), DBType.JPA);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public LocationDTO updateLocation(@RequestBody LocationDTO location) {
		return convertToDTO(service.updateLocation(convertToJPAEntity(location)), DBType.JPA);
	}

	public LocationDTO convertToDTO(Object obj, DBType type) {
		LocationDTO dto = new LocationDTO();
		if (obj == null) {
			throw new NoDataFoundException("Not found location");
		}
		if (type == DBType.JPA) {
			Location location = (Location) obj;
			dto.setLocationId(location.getLocationId());
			dto.setCountry(location.getCountry());
			dto.setCity(location.getCity());
			dto.setCreatedAt(location.getCreatedAt());
			dto.setModifiedAt(location.getModifiedAt());
		} else if (type == DBType.CASSANDRA) {
			LocationCass location = (LocationCass) obj;
			dto.setLocationId(location.getLocationId());
			dto.setCountry(location.getCountry());
			dto.setCity(location.getCity());
			dto.setCreatedAt(location.getCreatedAt());
			dto.setModifiedAt(location.getModifiedAt());
		} else {
			throw new BadRequestException("No type");
		}
		return dto;
	}

	public Location convertToJPAEntity(LocationDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		Location location = new Location();
		location.setLocationId(dto.getLocationId());
		location.setCountry(dto.getCountry());
		location.setCity(dto.getCity());
		location.setCreatedAt(dto.getCreatedAt());
		location.setModifiedAt(dto.getModifiedAt());
		return location;
	}

	public LocationCass convertToCassandraEntity(LocationDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		LocationCass location = new LocationCass();
		location.setLocationId(dto.getLocationId());
		location.setCountry(dto.getCountry());
		location.setCity(dto.getCity());
		location.setCreatedAt(dto.getCreatedAt());
		location.setModifiedAt(dto.getModifiedAt());
		return location;
	}
}
