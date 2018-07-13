package com.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;
import com.training.dto.LocationDTO;
import com.training.exception.BadRequestException;
import com.training.exception.NoDataFoundException;
import com.training.model.DBType;
import com.training.model.cassandra.LocationCass;
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
	public ResponseEntity<LocationDTO> addLocation(@RequestBody LocationDTO location) {
		LocationDTO result = convertToDTO(service.addLocation(convertToJPAEntity(location)), DBType.JPA);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/location?id=" + result.getLocationId());
		return new ResponseEntity<LocationDTO>(result, headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<LocationDTO> updateLocation(@RequestBody LocationDTO location) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/location?id=" + location.getLocationId());
		return new ResponseEntity<LocationDTO>(
				convertToDTO(service.updateLocation(convertToJPAEntity(location)), DBType.JPA), headers, HttpStatus.OK);
	}

	@GetMapping(value = "/jpa/querydsl")
	public ResponseEntity<List<LocationDTO>> getLocationByQueryDslFromJpa(
			@QuerydslPredicate(root = Location.class) Predicate predicate) {
		List<Location> list = service.getLocationByQueryDslFromJpa(predicate);
		List<LocationDTO> dtoList = list.stream().map(product -> convertToDTO(product, DBType.JPA))
				.collect(Collectors.toList());
		return new ResponseEntity<List<LocationDTO>>(dtoList, HttpStatus.OK);
	}

	public LocationDTO convertToDTO(Object obj, DBType type) {
		LocationDTO dto = null;
		if (obj == null) {
			throw new NoDataFoundException("Not found location");
		}
		if (type == DBType.JPA) {
			Location location = (Location) obj;
			dto = new LocationDTO(location.getLocationId(), location.getCountry(), location.getCity(),
					location.getCreatedAt(), location.getModifiedAt());
		} else if (type == DBType.CASSANDRA) {
			LocationCass location = (LocationCass) obj;
			dto = new LocationDTO(location.getLocationId(), location.getCountry(), location.getCity(),
					location.getCreatedAt(), location.getModifiedAt());
		} else {
			throw new BadRequestException("No type");
		}
		return dto;
	}

	public Location convertToJPAEntity(LocationDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		Location location = new Location(dto.getLocationId(), dto.getCountry(), dto.getCity(), dto.getCreatedAt(),
				dto.getModifiedAt());
		return location;
	}

	public LocationCass convertToCassandraEntity(LocationDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		LocationCass location = new LocationCass(dto.getLocationId(), dto.getCountry(), dto.getCity(),
				dto.getCreatedAt(), dto.getModifiedAt());
		return location;
	}
}
