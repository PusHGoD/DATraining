package com.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.TimeDTO;
import com.training.exception.BadRequestException;
import com.training.exception.NoDataFoundException;
import com.training.model.DBType;
import com.training.model.cassandra.TimeCass;
import com.training.model.jpa.Time;
import com.training.service.TimeService;

@RestController
@RequestMapping("/time")
public class TimeController {
	public static final Logger log = LoggerFactory.getLogger(TimeController.class);

	@Autowired
	private TimeService service;

	@GetMapping(value = "", headers = "Accept=application/json")
	public List<TimeDTO> getAllLocations() {
		List<TimeCass> list = service.getAllTimes();
		List<TimeDTO> dtoList = list.stream().map(time -> convertToDTO(time, DBType.CASSANDRA))
				.collect(Collectors.toList());
		return dtoList;
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public ResponseEntity<TimeDTO> addLocation(@RequestBody TimeDTO time) {
		TimeDTO result = convertToDTO(service.addTime(convertToJPAEntity(time)), DBType.JPA);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/time?id=" + result.getTimeId());
		return new ResponseEntity<TimeDTO>(result, headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public ResponseEntity<TimeDTO> updateLocation(@RequestBody TimeDTO time) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/time?id=" + time.getTimeId());
		return new ResponseEntity<TimeDTO>(convertToDTO(service.updateTime(convertToJPAEntity(time)), DBType.JPA),
				headers, HttpStatus.OK);
	}

	public TimeDTO convertToDTO(Object obj, DBType type) {
		TimeDTO dto = null;
		if (obj == null) {
			throw new NoDataFoundException("Not found time");
		}
		if (type == DBType.JPA) {
			Time time = (Time) obj;
			dto = new TimeDTO(time.getTimeId(), time.getMonth(), time.getQuarter(), time.getYear(), time.getCreatedAt(),
					time.getModifiedAt());
		} else if (type == DBType.CASSANDRA) {
			TimeCass time = (TimeCass) obj;
			dto = new TimeDTO(time.getTimeId(), time.getMonth(), time.getQuarter(), time.getYear(), time.getCreatedAt(),
					time.getModifiedAt());
		} else {
			throw new BadRequestException("No type");
		}
		return dto;
	}

	public Time convertToJPAEntity(TimeDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		Time time = new Time(dto.getTimeId(), dto.getMonth(), dto.getQuarter(), dto.getYear(), dto.getCreatedAt(),
				dto.getModifiedAt());
		return time;
	}

	public TimeCass convertToCassandraEntity(TimeDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		TimeCass time = new TimeCass(dto.getTimeId(), dto.getMonth(), dto.getQuarter(), dto.getYear(),
				dto.getCreatedAt(), dto.getModifiedAt());
		return time;
	}
}
